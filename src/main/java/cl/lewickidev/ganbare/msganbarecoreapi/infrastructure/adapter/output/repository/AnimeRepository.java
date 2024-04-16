package cl.lewickidev.ganbare.msganbareapi.infrastructure.adapter.output.repository;

import cl.lewickidev.ganbare.msganbareapi.infrastructure.adapter.output.entity.AnimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<AnimeEntity, Long> {

}
