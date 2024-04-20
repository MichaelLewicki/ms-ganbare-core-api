package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "EPISODE")
public class EpisodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EPISODE")
    private Long id;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ID_ANIME")
    @JsonBackReference
    private AnimeEntity anime;

}
