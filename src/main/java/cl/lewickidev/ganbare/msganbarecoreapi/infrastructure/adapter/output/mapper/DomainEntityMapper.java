package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mapper;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Character;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.AnimeEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DomainEntityMapper {

	//Anime...

	@Mapping(target = "characters", source = "characters")
	Anime toDTO(AnimeEntity entity);

	AnimeEntity toEntity(Anime dto);

	List<Anime> toAnimeDTOs(List<AnimeEntity> animeEntities);

	default Page<Anime> toAnimeDTOs(Page<AnimeEntity> animeEntities) {
		return animeEntities.map(this::toDTO);
	}

	//Character...

	Character toDTO(CharacterEntity entity);

	@Mapping(target = "anime", ignore = true)
	CharacterEntity toEntity(Character dto);

	List<Character> toCharacterDTOs(List<CharacterEntity> characterEntities);

	default Page<Character> toCharacterDTOs(Page<CharacterEntity> characterEntities) {
		return characterEntities.map(this::toDTO);
	}

}
