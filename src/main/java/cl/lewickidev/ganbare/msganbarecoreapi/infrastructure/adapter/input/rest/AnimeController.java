package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
