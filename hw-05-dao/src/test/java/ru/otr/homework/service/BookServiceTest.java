package ru.otr.homework.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.impl.OutputServiceStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса по работе с книгами")
@SpringBootTest(classes = Application.class)
public class BookServiceTest {

    @MockBean
    OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookService bookService;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    public void prepareTestingData() {
        jdbc.update("DELETE books", Map.of());
        jdbc.update("DELETE authors", Map.of());
        jdbc.update("DELETE genres", Map.of());
        jdbc.update("DELETE assoc", Map.of());
    }

    @Test
    public void updateTest() {
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-0', 'test title 0')", Map.of());
        bookService.updateTitle(new Book("XXX-X-XXX-XXXXX-0", "New title 0"));
        verify(outputServiceStreams).outString("Updated 1 book(s)");
        reset(outputServiceStreams);
        bookService.updateTitle(new Book("XXX-X-XXX-XXXXX-0", null));
        verify(outputServiceStreams).outString("Updated 0 book(s)");
        reset(outputServiceStreams);
        bookService.updateTitle(new Book("XXX-X-XXX-XXXXX-0", ""));
        verify(outputServiceStreams).outString("Updated 0 book(s)");
        reset(outputServiceStreams);
    }

    @Test
    public void addTest() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(null, "Автор Тест"));
        authorList.add(new Author(null, "Автор Тест 2"));
        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre(null, "Жанр"));

        Book book = new Book("XXX-X-XXX-XXXXX-0", "New title 0", authorList, genreList);
        bookService.add(book);
        verify(outputServiceStreams).outString(String.format("Book added. ISBN: %s", book.getIsbn()));
    }

    @Test
    void deleteByIdTest() {
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-0', 'test title 0')", Map.of());
        bookService.deleteById("XXX-X-XXX-XXXXX-0");
        verify(outputServiceStreams).outString("Book deleted. ID: XXX-X-XXX-XXXXX-0");
    }

    @Test
    void getAllTest() {
        bookService.getAll();
        verify(outputServiceStreams).outString("Total books: 0\n");

        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-0', 'test title 0')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-1', 'test title 1')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-2', 'test title 2')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-3', 'test title 3')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-4', 'test title 4')", Map.of());
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-5', 'test title 5')", Map.of());

        bookService.getAll();
        verify(outputServiceStreams).outString("Total books: 6\n" +
                "Title: test title 0 (ISBN: XXX-X-XXX-XXXXX-0)\n" +
                "Genre: \n" +
                "\n" +
                "Authors: \n" +
                "\n" +
                "---------------------------------------\n" +
                "\n" +
                "Title: test title 1 (ISBN: XXX-X-XXX-XXXXX-1)\n" +
                "Genre: \n" +
                "\n" +
                "Authors: \n" +
                "\n" +
                "---------------------------------------\n" +
                "\n" +
                "Title: test title 2 (ISBN: XXX-X-XXX-XXXXX-2)\n" +
                "Genre: \n" +
                "\n" +
                "Authors: \n" +
                "\n" +
                "---------------------------------------\n" +
                "\n" +
                "Title: test title 3 (ISBN: XXX-X-XXX-XXXXX-3)\n" +
                "Genre: \n" +
                "\n" +
                "Authors: \n" +
                "\n" +
                "---------------------------------------\n" +
                "\n" +
                "Title: test title 4 (ISBN: XXX-X-XXX-XXXXX-4)\n" +
                "Genre: \n" +
                "\n" +
                "Authors: \n" +
                "\n" +
                "---------------------------------------\n" +
                "\n" +
                "Title: test title 5 (ISBN: XXX-X-XXX-XXXXX-5)\n" +
                "Genre: \n" +
                "\n" +
                "Authors: \n" +
                "\n" +
                "---------------------------------------\n");
    }

    @Test
    void getByIdTest() {
        jdbc.update("insert into BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-4', 'test title 4')", Map.of());
        bookService.getById("XXX-X-XXX-XXXXX-4");
        verify(outputServiceStreams).outString("Title: test title 4 (ISBN: XXX-X-XXX-XXXXX-4)\n" +
                "Genre: \n" +
                "\n" +
                "Authors: \n" +
                "\n" +
                "---------------------------------------\n");
    }
}
