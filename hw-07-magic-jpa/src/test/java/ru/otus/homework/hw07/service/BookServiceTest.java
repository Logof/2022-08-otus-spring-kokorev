package ru.otus.homework.hw07.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.entity.Book;
import ru.otus.homework.hw07.entity.Genre;
import ru.otus.homework.hw07.exception.FieldRequiredException;
import ru.otus.homework.hw07.service.impl.BookServiceImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест сервиса по работе с книгами")
@DataJpaTest
@Import(BookServiceImpl.class)
public class BookServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookService bookService;

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')"})
    public void updateTitleTest() {
        Book bookActual = bookService.getByIsbn("978-5-04-094119-3");
        bookActual.setTitle("1000 лучших сказок всех времен и народов");
        Book bookExpected = bookService.updateTitle("978-5-04-094119-3", "1000 лучших сказок всех времен и народов");
        assertEquals(bookExpected, bookActual);

        Exception exceptionNull = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle("978-5-04-094119-3", null);
        });
        String expectedMessage = "Required field(s): isbn, title";
        assertTrue(exceptionNull.getMessage().contains(expectedMessage));

        Exception exceptionEmpty = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle("978-5-04-094119-3", "");
        });
        assertTrue(exceptionNull.getMessage().contains(expectedMessage));
    }

    @Test
    @Transactional
    public void addTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        Book bookExpected = bookService.add(bookActual);
        assertEquals(bookExpected, bookActual);
    }

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')"})
    void deleteByIdTest() {
        bookService.deleteByIsbn("978-5-04-094119-3");
        Book bookExpected = entityManager.find(Book.class, "978-5-04-094119-3");
        assertNull(bookExpected);
    }

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')," +
                    "                              ('978-5-699-12014-7', 'Колобок')"})
    void getAllTest() {
        List<Book> booksActual = new ArrayList<>();
        booksActual.add(new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов"));
        booksActual.add(new Book("978-5-699-12014-7", "Колобок"));

        List<Book> booksExpected = bookService.getAll();
        assertEquals(booksExpected, booksActual);
    }

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')"})
    void getByIdTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        Book bookExpected = bookService.getByIsbn("978-5-04-094119-3");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')",
            "insert into AUTHORS(id, full_name) values (1, 'Якоб и Вильгельм Гримм')",
            "insert into AUTHORS(id, full_name) values (2, 'Шарль Перро')",
            "insert into AUTHORS(id, full_name) values (3, 'Джозеф Джекобс')",
            "insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Якоб и Вильгельм Гримм'))",
            "insert into BOOK_AUTHORS(isbn, author_id) values ('978-5-04-094119-3', (select id from AUTHORS where full_name = 'Шарль Перро'))"})
    void getAllByAuthorTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        bookActual.getAuthors().add(new Author(1L, "Якоб и Вильгельм Гримм"));
        bookActual.getAuthors().add(new Author(2L, "Шарль Перро"));

        List<Book> booksExpected1 = bookService.getAllByAuthor("Гримм");
        assertEquals(booksExpected1, Collections.singletonList(bookActual));

        List<Book> booksExpected2 = bookService.getAllByAuthor("Джекобс");
        assertEquals(booksExpected2, new ArrayList<>());
    }

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')",
            "insert into GENRES(id, genre_name) values (1, 'Сказки')",
            "insert into GENRES(id, genre_name) values (2, 'Фантастика')",
            "insert into BOOK_GENRES(isbn, genre_id) values ('978-5-04-094119-3', (select id from GENRES where genre_name = 'Сказки'))"
    })
    void getAllByGenreTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        bookActual.getGenres().add(new Genre(1L, "Сказки"));

        List<Book> booksExpected1 = bookService.getAllByGenre("Сказки");
        assertEquals(booksExpected1, Collections.singletonList(bookActual));

        List<Book> booksExpected2 = bookService.getAllByGenre("Фантастика");
        assertEquals(booksExpected2, new ArrayList<>());

    }

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')",
            "insert into GENRES(id, genre_name) values (1, 'Сказки')"
    })
    void addGenreToBook() {
        Book book = bookService.getByIsbn("978-5-04-094119-3");
        List<Genre> genresActual = new ArrayList<>();
        genresActual.add(new Genre(1L, "Сказки"));

        bookService.addGenreToBook("978-5-04-094119-3", "Сказки");
        Book bookExpected = bookService.getByIsbn("978-5-04-094119-3");
        assertNotNull(bookExpected);
        assertNotNull(bookExpected.getGenres());
        assertEquals(bookExpected.getGenres(), genresActual);

        bookService.addGenreToBook("978-5-04-094119-3", "Фантастика");
        assertNotNull(bookExpected);
        assertNotNull(bookExpected.getGenres());

        boolean isFantastic = false;
        for (Genre genre : bookExpected.getGenres()) {
            if (genre.getGenreName().equals("Фантастика")) {
                isFantastic = true;
            }
        }
        ;

        assertTrue(isFantastic);
    }

    @Test
    @Transactional
    @Sql(statements = {
            "insert into BOOKS(isbn, title) values ('978-5-04-094119-3', '100 лучших сказок всех времен и народов')",
            "insert into AUTHORS(id, full_name) values (1, 'Якоб и Вильгельм Гримм')"
    })
    void addAuthorToBook() {
        Book book = bookService.getByIsbn("978-5-04-094119-3");
        List<Author> authorsActual = new ArrayList<>();
        authorsActual.add(new Author(1L, "Якоб и Вильгельм Гримм"));

        bookService.addAuthorToBook("978-5-04-094119-3", "Якоб и Вильгельм Гримм");
        Book bookExpected = bookService.getByIsbn("978-5-04-094119-3");
        List<Author> authorsExpected = bookExpected.getAuthors();
        assertNotNull(bookExpected);
        assertNotNull(bookExpected.getAuthors());
        assertEquals(authorsExpected, authorsActual);

        bookService.addAuthorToBook("978-5-04-094119-3", "Шарль Перро");
        assertNotNull(bookExpected);
        assertNotNull(bookExpected.getAuthors());

        boolean isScharlPerro = false;
        for (Author author : bookExpected.getAuthors()) {
            if (author.getFullName().equals("Шарль Перро")) {
                isScharlPerro = true;
            }
        }
        ;

        assertTrue(isScharlPerro);

    }
}
