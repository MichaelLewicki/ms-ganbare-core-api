package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Character;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.AnimeEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.CharacterEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.mapper.DomainEntityMapper;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.repository.CharacterRepository;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output.AnimeOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output.CharacterOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CharacterAdapter implements CharacterOutputPort {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    @Autowired
    private AnimeOutputPort animeOutputPort;

    @Override
    @Transactional
    public Character postCharacterByAnimeId(Long idAnime, Character character) throws HandledException {
        Anime animeFound = animeOutputPort.findAnimeById(idAnime);
        AnimeEntity animeEntity = domainEntityMapper.toEntity(animeFound);
        CharacterEntity characterEntity = domainEntityMapper.toEntity(character);
        characterEntity.setAnime(animeEntity);
        return domainEntityMapper.toDTO(characterRepository.save(characterEntity));
    }

    @Override
    @Transactional
    public Character updateCharacterById(Character character, Long idCharacter) throws HandledException {
        CharacterEntity characterFound = characterRepository.findById(idCharacter)
                .orElseThrow(() -> new HandledException("404", "Character not found"));
        characterFound.setName(character.getName());
        characterFound.setDescription(character.getDescription());
        characterFound.setImage(character.getImage());
        return domainEntityMapper.toDTO(characterRepository.save(characterFound));
    }

    @Override
    @Transactional
    public Character findCharacterById(Long idCharacter) throws HandledException {
        CharacterEntity characterFound = characterRepository.findById(idCharacter)
                .orElseThrow(() -> new HandledException("404", "Character not found"));
        return domainEntityMapper.toDTO(characterRepository.save(characterFound));
    }

    @Override
    @Transactional
    public Page<Character> findAllCharacters(Pageable pageable) {
        Page<CharacterEntity> characterList = characterRepository.findAll(pageable);
        return domainEntityMapper.toCharacterDTOs(characterList);
    }

    @Override
    @Transactional
    public Message deleteCharacterById(Long idCharacter) throws HandledException {
        CharacterEntity characterFound = characterRepository.findById(idCharacter)
                .orElseThrow(() -> new HandledException("404", "Character not found"));
        characterRepository.delete(characterFound);
        AnimeEntity anime = characterFound.getAnime();
        if (anime != null) {
            anime.getCharacters().remove(characterFound);
        }
        return new Message("record deleted");
    }
}
