package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.imp;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Episode;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.EpisodeController;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.input.EpisodeInputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
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
public class EpisodeControllerImp implements EpisodeController {

    @Autowired
    private EpisodeInputPort episodeInputPort;

    @Override
    public ResponseEntity<Episode> postEpisodeByAnimeId(Long idAnime, Episode episode) throws HandledException {
        if (episode.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[postEpisodeByAnimeId] Request payload: {}", episode.toString());
        Episode result = episodeInputPort.postEpisodeByAnimeId(idAnime, episode);
        log.info("[postEpisodeByAnimeId] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<Episode> updateEpisodeById(Episode episode, Long idEpisode) throws HandledException {
        if (episode.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[updateEpisodeById] Request resource: {} ", idEpisode.toString());
        log.info("[updateEpisodeById] Request payload: {} ", episode.toString());
        Episode result = episodeInputPort.updateEpisodeById(episode, idEpisode);
        log.info("[updateEpisodeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Episode> findEpisodeById(Long idEpisode) throws HandledException {
        log.info("[findEpisodeById] Request resource: {}", idEpisode.toString());
        Episode result = episodeInputPort.findEpisodeById(idEpisode);
        log.info("[findEpisodeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Page<Episode>> findAllEpisodes(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("[findAllEpisodes] Request: findAll");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Episode> result = episodeInputPort.findAllEpisodes(pageable);
        log.info("[findAllEpisodes] Response: {}", result.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Message> deleteEpisodeById(Long idEpisode) throws HandledException {
        log.info("[deleteEpisodeById] Request resource: {}", idEpisode.toString());
        Message result = episodeInputPort.deleteEpisodeById(idEpisode);
        log.info("[deleteEpisodeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
