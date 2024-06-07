package cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.imp;


import cl.lewickidev.ganbare.msganbarecoreapi.domain.dto.Message;
import cl.lewickidev.ganbare.msganbarecoreapi.domain.model.Anime;
import cl.lewickidev.ganbare.msganbarecoreapi.shared.exception.HandledException;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.adapter.input.rest.AnimeController;
import cl.lewickidev.ganbare.msganbarecoreapi.infrastructure.port.input.AnimeInputPort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
public class AnimeControllerImp implements AnimeController {

    @Autowired
    private AnimeInputPort animeInputPort;

    @Override
    public ResponseEntity<Anime> postAnime(@Valid Anime anime) throws HandledException {
        if (anime.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[postAnime] Request payload: {}", anime.toString());
        Anime result = animeInputPort.postAnime(anime);
        log.info("[postAnime] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<Anime> updateAnimeById(@Valid Anime anime, @Valid @NotNull Long idAnime) throws HandledException {
        if (anime.getId() != null) {
            throw new HandledException("400", "The request doesn't need to insert the ID into the payload");
        }
        log.info("[updateAnimeById] Request resource: {} ", idAnime.toString());
        log.info("[updateAnimeById] Request payload: {} ", anime.toString());
        Anime result = animeInputPort.updateAnimeById(anime, idAnime);
        log.info("[updateAnimeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Anime> findAnimeById(@Valid @NotNull Long idAnime) throws HandledException {
        log.info("[findAnimeById] Request resource: {}", idAnime.toString());
        Anime result = animeInputPort.findAnimeById(idAnime);
        log.info("[findAnimeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Page<Anime>> findAllAnimes(Integer pageNo, Integer pageSize, String sortBy){
        log.info("[findAllAnimes] Request: findAll");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Anime> result = animeInputPort.findAllAnimes(pageable);
        log.info("[findAllAnimes] Response: {}", result.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @Override
    public ResponseEntity<Message> deleteAnimeById(@Valid @NotNull Long idAnime) throws HandledException {
        log.info("[deleteAnimeById] Request resource: {}", idAnime.toString());
        Message result = animeInputPort.deleteAnimeById(idAnime);
        log.info("[deleteAnimeById] Response: {}", result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<List<Anime>> findAnimes() throws HandledException {
        // Inicializar el WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        WebDriver driver = new ChromeDriver(options);
        List<Anime> animesList = new ArrayList<>();

        String url = "https://www.animeid.tv/letra/a";
        driver.get(url);
        for (int i = 0; 6 > i; i++) {
            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

                // Encontrar todos los artículos de anime en la página
                List<WebElement> animeElements = driver.findElements(By.cssSelector("section#result article"));

                log.info("Número de elementos encontrados: " + animeElements.size());

                for (WebElement animeElement : animeElements) {
                    Anime anime = new Anime();

                    try {
                        WebElement elementTitle = animeElement.findElement(By.cssSelector("a header"));
                        if (elementTitle != null) {
                            String title = elementTitle.getText();
                            anime.setName(title);
                            log.info("Título encontrado: " + title);
                        }
                    } catch (NoSuchElementException e) {
                        log.warn("Título no encontrado en uno de los elementos.");
                    }

                    try {
                        WebElement elementDescription = animeElement.findElement(By.cssSelector("p div"));
                        if (elementDescription != null) {
                            String description = elementDescription.getText();
                            anime.setDescription(description);
                            log.info("Descripción encontrada: " + description);
                        }
                    } catch (NoSuchElementException e) {
                        log.warn("Descripción no encontrada en uno de los elementos.");
                    }
                    animesList.add(anime);
                }
                WebElement nextButton = driver.findElement(By.cssSelector("#paginas > ul > li:nth-child(10) > a"));
                nextButton.click();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Cerrar el WebDriver
                driver.quit();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(animesList);
    }

}
