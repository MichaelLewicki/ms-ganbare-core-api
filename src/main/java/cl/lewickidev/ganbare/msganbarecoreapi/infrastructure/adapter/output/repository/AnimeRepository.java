package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.repository;

import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.AnimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<AnimeEntity, Long> {

}
