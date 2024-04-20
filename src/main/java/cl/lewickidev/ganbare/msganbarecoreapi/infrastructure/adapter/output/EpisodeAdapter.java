package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Episode;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.AnimeEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.EpisodeEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mapper.DomainEntityMapper;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.repository.EpisodeRepository;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output.AnimeOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.output.EpisodeOutputPort;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EpisodeAdapter implements EpisodeOutputPort {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private DomainEntityMapper domainEntityMapper;

    @Autowired
    private AnimeOutputPort animeOutputPort;

    @Override
    public Episode postEpisodeByAnimeId(Long idAnime, Episode episode) throws HandledException {
        Anime animeFound = animeOutputPort.findAnimeById(idAnime);
        AnimeEntity animeEntity = domainEntityMapper.toEntity(animeFound);
        EpisodeEntity episodeEntity = domainEntityMapper.toEntity(episode);
        episodeEntity.setAnime(animeEntity);
        return domainEntityMapper.toDTO(episodeRepository.save(episodeEntity));
    }

    @Override
    public Episode updateEpisodeById(Episode episode, Long idEpisode) throws HandledException {
        EpisodeEntity episodeFound = episodeRepository.findById(idEpisode)
                .orElseThrow(() -> new HandledException("404", "Episode not found"));
        episodeFound.setNumber(episode.getNumber());
        episodeFound.setTitle(episode.getTitle());
        return domainEntityMapper.toDTO(episodeRepository.save(episodeFound));
    }

    @Override
    public Episode findEpisodeById(Long idEpisode) throws HandledException {
        EpisodeEntity episodeFound = episodeRepository.findById(idEpisode)
                .orElseThrow(() -> new HandledException("404", "Episode not found"));
        return domainEntityMapper.toDTO(episodeRepository.save(episodeFound));
    }

    @Override
    public Page<Episode> findAllEpisodes(Pageable pageable) {
        Page<EpisodeEntity> episodeList = episodeRepository.findAll(pageable);
        return domainEntityMapper.toEpisodeDTOs(episodeList);
    }

    @Override
    public Message deleteEpisodeById(Long idEpisode) throws HandledException {
        EpisodeEntity episodeFound = episodeRepository.findById(idEpisode)
                .orElseThrow(() -> new HandledException("404", "Episode not found"));
        episodeRepository.delete(episodeFound);
        return new Message("record deleted");
    }

}
