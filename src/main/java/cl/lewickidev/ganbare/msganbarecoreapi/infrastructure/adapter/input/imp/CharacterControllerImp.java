package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.imp;


import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Character;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.CharacterController;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.input.CharacterInputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CharacterControllerImp implements CharacterController {

    @Autowired
    private CharacterInputPort characterInputPort;

    @Override
    public ResponseEntity<Character> postCharacterByAnimeId(@Valid @NotNull Long idAnimme, Character character) throws HandledException {
        if (character.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[postCharacter] Request payload: {}", character.toString());
        Character result = characterInputPort.postCharacterByAnimeId(idAnimme, character);
        log.info("[postCharacter] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<Character> updateCharacterById(Character character, Long idCharacter) throws HandledException {
        if (character.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[updateCharacterById] Request resource: {} ", idCharacter.toString());
        log.info("[updateCharacterById] Request payload: {} ", character.toString());
        Character result = characterInputPort.updateCharacterById(character, idCharacter);
        log.info("[updateCharacterById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Character> findCharacterById(Long idCharacter) throws HandledException {
        log.info("[findCharacterById] Request resource: {}", idCharacter.toString());
        Character result = characterInputPort.findCharacterById(idCharacter);
        log.info("[findCharacterById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Page<Character>> findAllCharacter(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("[findAllCharacters] Request: findAll");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Character> result = characterInputPort.findAllCharacters(pageable);
        log.info("[findAllCharacters] Response: {}", result.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Message> deleteCharacterById(Long idCharacter) throws HandledException {
        log.info("[deleteCharacterById] Request resource: {}", idCharacter.toString());
        Message result = characterInputPort.deleteCharacterById(idCharacter);
        log.info("[deleteCharacterById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
