package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.input;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Character;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterInputPort {

    Character postCharacterByAnimeId(Long idAnime, Character character) throws HandledException;

    Character updateCharacterById(Character character, Long idCharacter) throws HandledException;

    Character findCharacterById(Long idCharacter) throws HandledException;

    Page<Character> findAllCharacters(Pageable pageable);

    Message deleteCharacterById(Long idCharacter) throws HandledException;

}
