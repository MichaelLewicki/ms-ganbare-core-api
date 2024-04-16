package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ANIME")
public class AnimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "YEAR", length = 4)
    private Integer year;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;
    @Column(name = "IMAGE")
    private String image;

    //private LinkedHashSet<CharacterEntity> characters = new LinkedHashSet<>();
    //private LinkedHashSet<EpisodeEntity> episodes = new LinkedHashSet<>();
    //private LinkedHashSet<GenreEntity> genres = new LinkedHashSet<>();

}
