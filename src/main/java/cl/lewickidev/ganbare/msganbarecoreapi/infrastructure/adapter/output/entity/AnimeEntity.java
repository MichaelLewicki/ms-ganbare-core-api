package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "ANIME")
public class AnimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANIME")
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "YEAR", length = 4)
    @NotNull(message = "Year cannot be null")
    private Integer year;

    @Column(name = "DESCRIPTION", length = 900)
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<CharacterEntity> characters = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<EpisodeEntity> episodes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "ANIME_GENRE",
            joinColumns = @JoinColumn(name = "ID_ANIME"),
            inverseJoinColumns = @JoinColumn(name = "ID_GENRE"))
    private List<GenreEntity> genres = new ArrayList<>();

}
