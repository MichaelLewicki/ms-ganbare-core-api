package cl.lewickidev.ganbare.msganbarecoreapi.application.port.output;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Episode;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EpisodeOutputPort {

    Episode postEpisodeByAnimeId(Long idAnime, Episode episode) throws HandledException;

    Episode updateEpisodeById(Episode episode, Long idEpisode) throws HandledException;

    Episode findEpisodeById(Long idEpisode) throws HandledException;

    Page<Episode> findAllEpisodes(Pageable pageable);

    Message deleteEpisodeById(Long idEpisode) throws HandledException;

}
