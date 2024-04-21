package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.imp;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Genre;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.GenreController;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.input.GenreInputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
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
public class GenreControllerImp implements GenreController {

    @Autowired
    private GenreInputPort genreInputPort;

    @Override
    public ResponseEntity<Genre> postGenre(Genre genre) throws HandledException {
        if (genre.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[postGenre] Request payload: {}", genre.toString());
        Genre result = genreInputPort.postGenre(genre);
        log.info("[postGenre] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<Genre> updateGenreById(Genre genre, Long idGenre) throws HandledException {
        if (genre.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[updateGenreById] Request resource: {} ", idGenre.toString());
        log.info("[updateGenreById] Request payload: {} ", genre.toString());
        Genre result = genreInputPort.updateGenreById(genre, idGenre);
        log.info("[updateGenreById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Genre> findGenreById(Long idGenre) throws HandledException {
        log.info("[findGenreById] Request resource: {}", idGenre.toString());
        Genre result = genreInputPort.findGenreById(idGenre);
        log.info("[findGenreById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Page<Genre>> findAllGenres(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("[findAllGenres] Request: findAll");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Genre> result = genreInputPort.findAllGenres(pageable);
        log.info("[findAllGenres] Response: {}", result.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Message> deleteGenreById(Long idGenre) throws HandledException {
        log.info("[deleteGenreById] Request resource: {}", idGenre.toString());
        Message result = genreInputPort.deleteGenreById(idGenre);
        log.info("[deleteGenreById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
