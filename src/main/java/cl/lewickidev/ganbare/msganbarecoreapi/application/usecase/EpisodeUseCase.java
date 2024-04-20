package cl.lewickidev.ganbare.msganbarecoreapi.application.usecase;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Episode;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.input.EpisodeInputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output.EpisodeOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EpisodeUseCase implements EpisodeInputPort {

    @Autowired
    private EpisodeOutputPort episodeOutputPort;

    @Override
    public Episode postEpisodeByAnimeId(Long idAnime, Episode episode) throws HandledException {
        return episodeOutputPort.postEpisodeByAnimeId(idAnime, episode);
    }

    @Override
    public Episode updateEpisodeById(Episode episode, Long idEpisode) throws HandledException {
        return episodeOutputPort.updateEpisodeById(episode, idEpisode);
    }

    @Override
    public Episode findEpisodeById(Long idEpisode) throws HandledException {
        return episodeOutputPort.findEpisodeById(idEpisode);
    }

    @Override
    public Page<Episode> findAllEpisodes(Pageable pageable) {
        return episodeOutputPort.findAllEpisodes(pageable);
    }

    @Override
    public Message deleteEpisodeById(Long idEpisode) throws HandledException {
        return episodeOutputPort.deleteEpisodeById(idEpisode);
    }

}
