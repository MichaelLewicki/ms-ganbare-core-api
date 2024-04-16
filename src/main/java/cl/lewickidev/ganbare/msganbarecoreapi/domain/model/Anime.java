package cl.lewickidev.ganbare.msganbarecoreapi.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Anime {

    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Year cannot be null")
    private Integer year;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    private String image;
    //private LinkedHashSet<Character> characters = new LinkedHashSet<>();
    //private LinkedHashSet<Episode> episodes = new LinkedHashSet<>();
    //private LinkedHashSet<Genre> genres = new LinkedHashSet<>();

}
