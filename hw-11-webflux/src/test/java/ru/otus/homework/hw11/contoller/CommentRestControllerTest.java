package ru.otus.homework.hw11.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CommentRestControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void getAllCommentByIsbnTest() {
        webClient.get()
                .uri("/api/comment/1234")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
