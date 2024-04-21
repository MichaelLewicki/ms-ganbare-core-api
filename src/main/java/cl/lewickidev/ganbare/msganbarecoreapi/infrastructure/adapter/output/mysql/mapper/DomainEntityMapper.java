package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.mapper;

import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Character;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Episode;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Genre;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.AnimeEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.CharacterEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.EpisodeEntity;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;


@Mapper(componentModel = ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DomainEntityMapper {

	//Anime...

	Anime toDTO(AnimeEntity entity);

	AnimeEntity toEntity(Anime dto);

	//List<Anime> toAnimeDTOs(List<AnimeEntity> animeEntities);

	default Page<Anime> toAnimeDTOs(Page<AnimeEntity> animeEntities) {
		return animeEntities.map(this::toDTO);
	}

	//Character...

	Character toDTO(CharacterEntity entity);

	@Mapping(target = "anime", ignore = true)
	CharacterEntity toEntity(Character dto);

	//List<Character> toCharacterDTOs(List<CharacterEntity> characterEntities);

	default Page<Character> toCharacterDTOs(Page<CharacterEntity> characterEntities) {
		return characterEntities.map(this::toDTO);
	}

	//Episode...

	Episode toDTO(EpisodeEntity entity);

	@Mapping(target = "anime", ignore = true)
	EpisodeEntity toEntity(Episode dto);

	//List<Episode> toEpisodeDTOs(List<EpisodeEntity> episodeEntities);

	default Page<Episode> toEpisodeDTOs(Page<EpisodeEntity> episodeEntities) {
		return episodeEntities.map(this::toDTO);
	}

	//Genre...

	Genre toDTO(GenreEntity entity);

	GenreEntity toEntity(Genre dto);

	//List<Anime> toGenreDTOs(List<AnimeEntity> animeEntities);

	default Page<Genre> toGenreDTOs(Page<GenreEntity> genreEntities) {
		return genreEntities.map(this::toDTO);
	}

}
