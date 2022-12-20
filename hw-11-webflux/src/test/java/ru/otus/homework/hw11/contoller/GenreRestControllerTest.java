package ru.otus.homework.hw11.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import ru.otus.homework.hw11.entity.dto.GenreDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GenreRestControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void getAllGenresTest() throws Exception {
        webClient.get()
                .uri("/api/genre")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void saveGenreTest() {
        webClient.post()
                .uri("/api/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new GenreDto("Genre")))
                .exchange()
                .expectStatus()
                .isOk();
    }
}
