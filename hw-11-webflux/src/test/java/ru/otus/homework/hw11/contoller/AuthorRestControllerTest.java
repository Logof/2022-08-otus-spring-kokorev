package ru.otus.homework.hw11.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import ru.otus.homework.hw11.entity.dto.AuthorDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuthorRestControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void getAllAuthorsTest() throws Exception {
        webClient.get()
                .uri("/api/author")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void saveAuthorTest() {
        webClient.post()
                .uri("/api/author")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new AuthorDto("Author")))
                .exchange()
                .expectStatus()
                .isOk();
    }
}
