package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.repository;

import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.AnimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<AnimeEntity, Long> {

}
