package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Character;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/ganbare")
@CrossOrigin("*")
public interface CharacterController {

    @PostMapping(value = "anime/{idAnime}/character")
    public ResponseEntity<Character> postCharacterByAnimeId(@Valid @NotNull @PathVariable("idAnime") Long idAnime,
                                                            @RequestBody Character character) throws HandledException;

    @PutMapping("/character/{idCharacter}")
    public ResponseEntity<Character> updateCharacterById(@RequestBody Character character,
                                                         @Valid @NotNull @PathVariable("idCharacter")  Long idCharacter) throws HandledException;

    @GetMapping("/character/{idCharacter}")
    public ResponseEntity<Character> findCharacterById(@Valid @NotNull @PathVariable("idCharacter") Long idCharacter) throws HandledException;

    @GetMapping("/character")
    public ResponseEntity<Page<Character>> findAllCharacter(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy);


    @DeleteMapping("/character/{idCharacter}")
    public ResponseEntity<Message> deleteCharacterById(@Valid @NotNull @PathVariable("idCharacter") Long idCharacter) throws HandledException;

}
