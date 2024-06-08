package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.imp;


import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.exception.HandledException;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.AnimeController;
import cl.lewickidev.ganbare.msganbarecoreapi.application.port.input.AnimeInputPort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class AnimeControllerImp implements AnimeController {

    @Autowired
    private AnimeInputPort animeInputPort;

    @Override
    public ResponseEntity<Anime> postAnime(@Valid Anime anime) throws HandledException {
        if (anime.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[postAnime] Request payload: {}", anime.toString());
        Anime result = animeInputPort.postAnime(anime);
        log.info("[postAnime] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<Anime> updateAnimeById(@Valid Anime anime, @Valid @NotNull Long idAnime) throws HandledException {
        if (anime.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[updateAnimeById] Request resource: {} ", idAnime.toString());
        log.info("[updateAnimeById] Request payload: {} ", anime.toString());
        Anime result = animeInputPort.updateAnimeById(anime, idAnime);
        log.info("[updateAnimeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Anime> findAnimeById(@Valid @NotNull Long idAnime) throws HandledException {
        log.info("[findAnimeById] Request resource: {}", idAnime.toString());
        Anime result = animeInputPort.findAnimeById(idAnime);
        log.info("[findAnimeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Page<Anime>> findAllAnimes(Integer pageNo, Integer pageSize, String sortBy){
        log.info("[findAllAnimes] Request: findAll");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Anime> result = animeInputPort.findAllAnimes(pageable);
        log.info("[findAllAnimes] Response: {}", result.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @Override
    public ResponseEntity<Message> deleteAnimeById(@Valid @NotNull Long idAnime) throws HandledException {
        log.info("[deleteAnimeById] Request resource: {}", idAnime.toString());
        Message result = animeInputPort.deleteAnimeById(idAnime);
        log.info("[deleteAnimeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
