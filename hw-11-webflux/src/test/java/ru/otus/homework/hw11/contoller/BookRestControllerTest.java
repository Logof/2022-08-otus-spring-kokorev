package ru.otus.homework.hw11.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.hw11.entity.Book;
import ru.otus.homework.hw11.entity.dto.BookDto;
import ru.otus.homework.hw11.repository.BookRepository;
import ru.otus.homework.hw11.repository.CommentRepository;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BookRestControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    public void getAllBooksTest() throws Exception {
        when(bookRepository.findAll()).thenReturn(Flux.empty());
        webClient.get()
                .uri("/api/book")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getBookByIsbnTest() {
        BookDto actualBookDto = new BookDto("1234", "Заголовок");
        Book actualBook = new Book("1234", "Заголовок");

        when(bookRepository.findById(actualBookDto.getIsbn())).thenReturn(Mono.just(actualBook));

        webClient.get()
                .uri("/api/book/1234")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(BookDto.class)
                .value(actual -> {
                    assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(actualBookDto);
        });
    }

    @Test
    void saveBookTest() {
        BookDto bookDto = new BookDto("1234", "Заголовок", new ArrayList<>(), new ArrayList<>());
        Book book = new Book("1234", "Заголовок", new ArrayList<>(), new ArrayList<>());

        when(bookRepository.save(any())).thenReturn(Mono.just(book));

        webClient.post()
                .uri("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookDto))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void deleteBookTest() {
        BookDto bookDto = new BookDto("1234", "Заголовок");
        when(bookRepository.deleteById(bookDto.getIsbn())).thenReturn(Mono.empty());

        webClient.delete()
                .uri("/api/book/" + bookDto.getIsbn())
                .exchange()
                .expectStatus()
                .isOk();

        verify(bookRepository, times(1)).deleteById(eq(bookDto.getIsbn()));
        //verify(commentRepository, times(1)).deleteByBook(eq(new Book("1234", "Заголовок")));
    }
}
