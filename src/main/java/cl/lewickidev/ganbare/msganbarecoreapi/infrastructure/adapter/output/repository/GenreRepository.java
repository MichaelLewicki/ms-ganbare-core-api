package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.repository;

import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
