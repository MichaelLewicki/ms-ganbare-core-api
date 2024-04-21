package cl.lewickidev.ganbare.msganbarecoreapi.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class Anime {

    private Long id;
    private String name;
    private Integer year;
    private String description;
    private String image;

    private List<Character> characters = new ArrayList<>();
    private List<Episode> episodes = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();

}
