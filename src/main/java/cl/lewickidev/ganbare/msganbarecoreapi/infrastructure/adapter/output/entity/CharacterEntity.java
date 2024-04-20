package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "CHARACTER_INFO")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CHARACTER")
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Column(name = "IMAGE")
    //@NotBlank(message = "Image cannot be blank")
    private String image;

    @ManyToOne
    @JoinColumn(name = "ID_ANIME")
    @JsonBackReference
    private AnimeEntity anime;

}
