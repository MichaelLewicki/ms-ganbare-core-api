package cl.lewickidev.ganbare.msganbarecoreapi.application.usecase;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Character;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.input.CharacterInputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.output.CharacterOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CharacterUseCase implements CharacterInputPort {

    @Autowired
    CharacterOutputPort characterOutputPort;

    @Override
    public Character postCharacterByAnimeId(Long idAnime, Character character) throws HandledException {
        return characterOutputPort.postCharacterByAnimeId(idAnime, character);
    }

    @Override
    public Character updateCharacterById(Character character, Long idCharacter) throws HandledException {
        return characterOutputPort.updateCharacterById(character, idCharacter);
    }

    @Override
    public Character findCharacterById(Long idCharacter) throws HandledException {
        return characterOutputPort.findCharacterById(idCharacter);
    }

    @Override
    public Page<Character> findAllCharacters(Pageable pageable) {
        return characterOutputPort.findAllCharacters(pageable);
    }

    @Override
    public Message deleteCharacterById(Long idCharacter) throws HandledException {
        return characterOutputPort.deleteCharacterById(idCharacter);
    }

}
