package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.repository;

import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

}
