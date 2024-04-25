package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.mysql.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "EPISODE")
@Entity
@ToString(exclude = "anime")
public class EpisodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EPISODE")
    private Long id;

    @Column(name = "NUMBER")
    @NotNull(message = "number cannot be null")
    private Integer number;

    @Column(name = "TITLE")
    @NotBlank(message = "title cannot be blank")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ID_ANIME")
    @JsonBackReference
    private AnimeEntity anime;

}
