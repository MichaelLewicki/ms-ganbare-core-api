package cl.lewickidev.ganbare.msganbarecoreapi.application.usecase;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.input.AnimeInputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.output.AnimeOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimeUseCase implements AnimeInputPort {

    @Autowired
    private AnimeOutputPort animeOutputPort;

    @Override
    public Anime postAnime(Anime anime) throws HandledException {
        return animeOutputPort.postAnime(anime);
    }

    @Override
    public Anime updateAnimeById(Anime anime, Long idAnime) throws HandledException {
        return animeOutputPort.updateAnimeById(anime, idAnime);
    }

    @Override
    public Anime findAnimeById(Long idAnime) throws HandledException {
        return animeOutputPort.findAnimeById(idAnime);
    }

    @Override
    public Page<Anime> findAllAnimes(Pageable pageable) {
        return animeOutputPort.findAllAnimes(pageable);
    }

    @Override
    public Message deleteAnimeById(Long idAnime) throws HandledException {
        return animeOutputPort.deleteAnimeById(idAnime);
    }
}
