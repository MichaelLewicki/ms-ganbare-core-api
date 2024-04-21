package cl.lewickidev.ganbare.msganbarecoreapi.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Genre {

    private Long id;
    private String name;

    private List<Anime> animes = new ArrayList<>();

}
