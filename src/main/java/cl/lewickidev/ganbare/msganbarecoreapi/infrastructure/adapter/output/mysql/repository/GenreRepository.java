package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.repository;

import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
