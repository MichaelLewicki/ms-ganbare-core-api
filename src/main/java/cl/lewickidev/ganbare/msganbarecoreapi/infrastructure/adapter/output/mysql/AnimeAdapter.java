package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Genre;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.GenreEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.output.GenreOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.AnimeEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.mapper.DomainEntityMapper;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.repository.AnimeRepository;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.output.AnimeOutputPort;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AnimeAdapter implements AnimeOutputPort {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    @Autowired
    private GenreOutputPort genreOutputPort;

    @Override
    @Transactional
    public Anime postAnime(Anime anime) throws HandledException {
        List<Genre> genresToAdd = new ArrayList<>();
        List<Genre> genresToFind = anime.getGenres();
        if (genresToFind != null) {
            for (Genre g: genresToFind) {
                genresToAdd.add(genreOutputPort.findGenreById(g.getId()));
            }
        }
        AnimeEntity animeEntity = domainEntityMapper.toEntity(anime);
        List<GenreEntity> genreEntities = domainEntityMapper.toGenreEntities(genresToAdd);
        if (genreEntities != null) {
            genreEntities.forEach(genreEntity ->
                    genreEntity.getAnimes().add(animeEntity));
        }
        animeEntity.setGenres(genreEntities);
        return domainEntityMapper.toDTO(animeRepository.save(animeEntity));
    }

    @Override
    @Transactional
    public Anime updateAnimeById(Anime anime, Long idAnime) throws HandledException {
        AnimeEntity animeFound = animeRepository.findById(idAnime)
                .orElseThrow(() -> new HandledException("404", "Anime not found"));
        animeFound.setName(anime.getName());
        animeFound.setYear(anime.getYear());
        animeFound.setDescription(anime.getDescription());
        animeFound.setImage(anime.getImage());
        if (anime.getGenres() != null && !anime.getGenres().isEmpty()) {
            updateRelationshipsWithGenres(anime.getGenres(), animeFound);
        }
        return domainEntityMapper.toDTO(animeRepository.save(animeFound));
    }

    @Override
    @Transactional
    public Anime findAnimeById(Long idAnime) throws HandledException {
        AnimeEntity animeFound = animeRepository.findById(idAnime)
                .orElseThrow(() -> new HandledException("404", "Anime not found"));
        return domainEntityMapper.toDTO(animeRepository.save(animeFound));
    }

    @Override
    @Transactional
    public Page<Anime> findAllAnimes(Pageable pageable) {
        Page<AnimeEntity> animeList = animeRepository.findAll(pageable);
        return domainEntityMapper.toAnimeDTOs(animeList);
    }

    @Override
    @Transactional
    public Message deleteAnimeById(Long idAnime) throws HandledException {
        AnimeEntity animeFound = animeRepository.findById(idAnime)
                .orElseThrow(() -> new HandledException("404", "Anime not found"));
        animeRepository.delete(animeFound);
        return new Message("record deleted");
    }

    //private methods...

    private void updateRelationshipsWithGenres(List<Genre> genresToFind, AnimeEntity animeFound) throws HandledException {
        List<GenreEntity> genreEntities = new ArrayList<>();
        List<Genre> genresFound = new ArrayList<>();
        for (Genre e: genresToFind) {
            genresFound.add(genreOutputPort.findGenreById(e.getId()));
        }
        genreEntities = domainEntityMapper.toGenreEntities(genresFound);
        animeFound.setGenres(genreEntities);
    }

}
