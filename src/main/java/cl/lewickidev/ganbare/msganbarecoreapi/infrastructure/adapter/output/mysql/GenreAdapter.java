package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Genre;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.GenreEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.mapper.DomainEntityMapper;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.repository.GenreRepository;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output.GenreOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GenreAdapter implements GenreOutputPort {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    @Override
    @Transactional
    public Genre postGenre(Genre genre) throws HandledException {
        GenreEntity genreEntity = domainEntityMapper.toEntity(genre);
        return domainEntityMapper.toDTO(genreRepository.save(genreEntity));
    }

    @Override
    @Transactional
    public Genre updateGenreById(Genre genre, Long idGenre) throws HandledException {
        GenreEntity genreFound = genreRepository.findById(idGenre)
                .orElseThrow(() -> new HandledException("404", "Genre not found"));
        genreFound.setName(genre.getName());
        return domainEntityMapper.toDTO(genreRepository.save(genreFound));
    }

    @Override
    @Transactional
    public Genre findGenreById(Long idGenre) throws HandledException {
        GenreEntity genreFound = genreRepository.findById(idGenre)
                .orElseThrow(() -> new HandledException("404", "Genre not found"));
        return domainEntityMapper.toDTO(genreRepository.save(genreFound));
    }

    @Override
    @Transactional
    public Page<Genre> findAllGenres(Pageable pageable) {
        Page<GenreEntity> genreList = genreRepository.findAll(pageable);
        return domainEntityMapper.toGenreDTOs(genreList);
    }

    @Override
    @Transactional
    public Message deleteGenreById(Long idGenre) throws HandledException {
        GenreEntity genreFound = genreRepository.findById(idGenre)
                .orElseThrow(() -> new HandledException("404", "Genre not found"));
        genreRepository.delete(genreFound);
        return new Message("record deleted");
    }

}
