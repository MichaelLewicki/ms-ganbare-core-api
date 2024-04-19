package cl.lewickidev.ganbare.msganbarecoreapi.domain.model;

import lombok.Data;

import java.util.LinkedHashSet;

@Data
public class Anime {

    private Long id;
    private String name;
    private Integer year;
    private String description;
    private String image;

    private LinkedHashSet<Character> characters = new LinkedHashSet<>();
    //private LinkedHashSet<Episode> episodes = new LinkedHashSet<>();
    //private LinkedHashSet<Genre> genres = new LinkedHashSet<>();

}
