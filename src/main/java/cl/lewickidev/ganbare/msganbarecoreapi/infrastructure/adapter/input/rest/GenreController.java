package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Genre;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/ganbare")
@CrossOrigin("*")
public interface GenreController {

    @PostMapping(value = "/genre")
    public ResponseEntity<Genre> postGenre(@Valid @RequestBody Genre genre) throws HandledException;

    @PutMapping("/genre/{idGenre}")
    public ResponseEntity<Genre> updateGenreById(@Valid @RequestBody Genre genre, @Valid @NotNull @PathVariable("idGenre") Long idGenre) throws HandledException;

    @GetMapping("/genre/{idGenre}")
    public ResponseEntity<Genre> findGenreById(@Valid @NotNull @PathVariable("idGenre") Long idGenre) throws HandledException;

    @GetMapping("/genre")
    public ResponseEntity<Page<Genre>> findAllGenres(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy);

    @DeleteMapping("/genre/{idGenre}")
    public ResponseEntity<Message> deleteGenreById(@Valid @NotNull @PathVariable("idGenre") Long idGenre) throws HandledException;

}
