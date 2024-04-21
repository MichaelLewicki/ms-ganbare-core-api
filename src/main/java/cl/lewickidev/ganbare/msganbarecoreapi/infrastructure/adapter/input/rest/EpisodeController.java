package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Episode;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/v1/ganbare")
@CrossOrigin("*")
public interface EpisodeController {

    @PostMapping(value = "anime/{idAnime}/episode")
    public ResponseEntity<Episode> postEpisodeByAnimeId(@Valid @NotNull @PathVariable("idAnime") Long idAnime,
                                                            @RequestBody Episode episode) throws HandledException;

    @PutMapping("/episode/{idEpisode}")
    public ResponseEntity<Episode> updateEpisodeById(@RequestBody Episode episode,
                                                         @Valid @NotNull @PathVariable("idEpisode")  Long idEpisode) throws HandledException;

    @GetMapping("/episode/{idEpisode}")
    public ResponseEntity<Episode> findEpisodeById(@Valid @NotNull @PathVariable("idEpisode") Long idEpisode) throws HandledException;

    @GetMapping("/episode")
    public ResponseEntity<Page<Episode>> findAllEpisodes(@RequestParam(defaultValue = "0") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam(defaultValue = "id") String sortBy);


    @DeleteMapping("/episode/{idEpisode}")
    public ResponseEntity<Message> deleteEpisodeById(@Valid @NotNull @PathVariable("idEpisode") Long idEpisode) throws HandledException;

}
