package cl.lewickidev.ganbare.msganbareapi.infrastructure.adapter.input;

import cl.lewickidev.ganbare.msganbareapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbareapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbareapi.shared.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/api/v1/ganbare")
@CrossOrigin("*")
public interface AnimeController {

    @PostMapping(value = "/anime")
    public ResponseEntity<Anime> postAnime(@Valid @RequestBody Anime anime) throws HandledException;

    @PutMapping("/anime/{idAnime}")
    public ResponseEntity<Anime> updateAnimeById(@Valid @RequestBody Anime anime, @Valid @NotNull @PathVariable("idAnime") Long idAnime) throws HandledException;

    @GetMapping("/anime/{idAnime}")
    public ResponseEntity<Anime> findAnimeById(@Valid @NotNull @PathVariable("idAnime") Long idAnime) throws HandledException;

    @GetMapping("/anime")
    public ResponseEntity<Page<Anime>> findAllAnimes(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy);


    @DeleteMapping("/anime/{idAnime}")
    public ResponseEntity<Message> deleteAnimeById(@Valid @NotNull @PathVariable("idAnime") Long idAnime) throws HandledException;

}
