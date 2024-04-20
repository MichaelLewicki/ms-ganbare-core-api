package cl.lewickidev.ganbare.msganbarecoreapi.domain.model;

import lombok.Data;

@Data
public class Character {

    private Long id;
    private String name;
    private String description;
    private String image;

}
