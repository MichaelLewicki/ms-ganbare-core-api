package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.repository;

import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Long> {

}
