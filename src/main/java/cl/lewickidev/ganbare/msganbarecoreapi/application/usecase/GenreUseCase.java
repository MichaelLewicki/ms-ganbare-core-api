package cl.lewickidev.ganbare.msganbarecoreapi.application.usecase;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Genre;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.input.GenreInputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output.GenreOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GenreUseCase implements GenreInputPort {

    @Autowired
    private GenreOutputPort genreOutputPort;

    @Override
    public Genre postGenre(Genre genre) throws HandledException {
        return genreOutputPort.postGenre(genre);
    }

    @Override
    public Genre updateGenreById(Genre genre, Long idGenre) throws HandledException {
        return genreOutputPort.updateGenreById(genre, idGenre);
    }

    @Override
    public Genre findGenreById(Long idGenre) throws HandledException {
        return genreOutputPort.findGenreById(idGenre);
    }

    @Override
    public Page<Genre> findAllGenres(Pageable pageable) {
        return genreOutputPort.findAllGenres(pageable);
    }

    @Override
    public Message deleteGenreById(Long idGenre) throws HandledException {
        return genreOutputPort.deleteGenreById(idGenre);
    }

}
