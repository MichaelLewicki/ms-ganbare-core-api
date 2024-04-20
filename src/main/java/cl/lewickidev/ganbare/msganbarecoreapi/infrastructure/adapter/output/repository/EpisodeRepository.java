package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.repository;

import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Long> {

}
