package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mapper;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.AnimeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DomainEntityMapper {

	Anime toDTO(AnimeEntity entity);

	AnimeEntity toEntity(Anime dto);

	//@Mapping(target = "genre", ignore = true)
	List<Anime> toAnimeDTOs(List<AnimeEntity> animeEntities);

	default Page<Anime> toAnimeDTOs(Page<AnimeEntity> animeEntities) {
		return animeEntities.map(this::toDTO);
	}

}
