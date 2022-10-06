package ru.otr.homework.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.entity.Book;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.impl.OutputServiceStreams;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по работе с книгами")
@SpringBootTest(classes = Application.class)
public class BookServiceTest {

    @MockBean
    private OutputServiceStreams ioService;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Autowired
    private BookService bookService;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    private void prepareTestingData() {
        jdbc.update("DELETE books", Map.of());
        jdbc.update("DELETE authors", Map.of());
        jdbc.update("DELETE genres", Map.of());
        jdbc.update("DELETE assoc", Map.of());
    }

    private void prepateData() {
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-0', 'test title 0')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-1', 'test title 1')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-2', 'test title 2')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-3', 'test title 3')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-4', 'test title 4')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-5', 'test title 5')", Map.of());
    }

    @Test
    public void updateTest() {
        prepateData();
        bookService.update("XXX-X-XXX-XXXXX-0", new Book("XXX-X-XXX-XXXXX-0", "New title 0"));
        bookService.update("XXX-X-XXX-XXXXX-1", new Book("XXX-X-XXX-XXXXX-8", null));
        bookService.update("XXX-X-XXX-XXXXX-2", new Book("XXX-X-XXX-XXXXX-9", "New title 8"));
    }

    @Test
    public void addTest() {

        Book book = new Book("XXX-X-XXX-XXXXX-0", "New title 0");
        bookService.add(book);


        String stringExpect = "";
        String stringActual = String.format("Book added. ISBN: %s", book.getIsbn());
        assertEquals(stringExpect, stringActual);
    }

    @Test
    void deleteByIdTest() {
        prepateData();
        bookService.deleteById("XXX-X-XXX-XXXXX-0");
    }

    @Test
    void getAllTest() {
        bookService.getAll();
        prepateData();
        bookService.getAll();
    }

    @Test
    void getByIdTest() {
        prepateData();
        bookService.getById("XXX-X-XXX-XXXXX-4");
    }
}
