package cl.lewickidev.ganbare.msganbarecoreapi.application.port.input;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Genre;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreInputPort {

    Genre postGenre(Genre genre) throws HandledException;

    Genre updateGenreById(Genre genre, Long idGenre) throws HandledException;

    Genre findGenreById(Long idGenre) throws HandledException;

    Page<Genre> findAllGenres(Pageable pageable);

    Message deleteGenreById(Long idGenre) throws HandledException;

}
