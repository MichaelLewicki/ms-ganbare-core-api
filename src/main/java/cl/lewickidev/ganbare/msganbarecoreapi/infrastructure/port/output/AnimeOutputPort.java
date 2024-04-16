package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimeOutputPort {

    public Anime postAnime(Anime anime) throws HandledException;

    public Anime updateAnimeById(Anime anime, Long idAnime) throws HandledException;

    public Anime findAnimeById(Long idAnime) throws HandledException;

    public Page<Anime> findAllAnimes(Pageable pageable);

    public Message deleteAnimeById(Long idAnime) throws HandledException;

}
