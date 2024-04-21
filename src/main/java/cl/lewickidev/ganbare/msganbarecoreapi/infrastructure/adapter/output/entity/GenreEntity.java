package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.output.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "GENRE")
@NoArgsConstructor
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GENRE")
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private List<AnimeEntity> animes = new ArrayList<>();

}
