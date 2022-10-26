package ru.otus.homework.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.repository.BookRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тест BookDao")
@DataJpaTest
@Import(BookRepositoryImpl.class)
public class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void clearData() {
        entityManager.clear();
    }

    @BeforeEach
    @Sql(statements = "DELETE FROM genres")
    void prepare() {

    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(bookActual);

        Book bookExpected = bookRepository.getBookByIsbn(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Обновление")
    @Test
    @Sql(statements = "INSERT INTO BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-X', 'Сказки народов мира')")
    void updateTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Сказки народов мира");
        bookActual.setTitle("Test Title");

        Book bookExpected = bookRepository.update(bookActual);
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Удаление")
    @Test
    @Sql(statements = "INSERT INTO BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-X', 'Сказки народов мира'), " +
            "('YYY-Y-YYY-YYYYY-Y', 'Сказки народов севера')")
    void deleteTest() {
        Book book1 = new Book("XXX-X-XXX-XXXXX-X", "Сказки народов мира");
        Book book2 = new Book("YYY-Y-YYY-YYYYY-Y", "Сказки народов севера");

        bookRepository.deleteByIsbn(book1.getIsbn());

        boolean book1RecordFound = false;
        boolean book2RecordFound = false;

        for (Book book : bookRepository.getAll()) {
            if (book.getTitle().equals(book1.getTitle())) {
                book1RecordFound = true;
            }
            if (book.getTitle().equals(book2.getTitle())) {
                book2RecordFound = true;
            }
        }
        assertFalse(book1RecordFound);
        assertTrue(book2RecordFound);
    }

    @DisplayName("Получение всех записей")
    @Test
    @Sql(statements = "INSERT INTO BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-X', 'Сказки народов мира'), " +
            "('YYY-Y-YYY-YYYYY-Y', 'Сказки народов севера')")
    void getAllTest() {
        Set<Book> bookActualList = new HashSet<>();
        bookActualList.add(new Book("XXX-X-XXX-XXXXX-X", "Сказки народов мира"));
        bookActualList.add(new Book("YYY-Y-YYY-YYYYY-Y", "Сказки народов севера"));

        Set<Book> booksExpectedList = bookRepository.getAll();

        assertEquals(booksExpectedList, bookActualList);
    }

    @DisplayName("Получение одной записи")
    @Test
    @Sql(statements = "INSERT INTO BOOKS(isbn, title) values ('XXX-X-XXX-XXXXX-X', 'Сказки народов мира'), " +
            "('YYY-Y-YYY-YYYYY-Y', 'Сказки народов севера')")
    void getByIdTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Сказки народов мира");
        Book bookExpected = bookRepository.getBookByIsbn(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }


    @DisplayName("Получение книг по автору")
    @Test
    @Sql(statements = {"INSERT INTO AUTHORS(id, full_name) values (1, 'Шарль Перро'), " +
            "(2, 'Якоб и Вильгельм Гримм'), (3, 'Андерсен Ханс Кристиан')",
            "INSERT INTO BOOKS (ISBN, TITLE) values ('XXX-XXX-XXX', 'Сказки Шарля Перро')",
            "INSERT INTO BOOK_AUTHORS (ISBN, AUTHOR_ID) values ('XXX-XXX-XXX', 1), ('XXX-XXX-XXX', 2)"})
    void getAllByAuthorTest() {
        Author author1 = new Author(1L, "Шарль Перро");
        Author author2 = new Author(2L, "Якоб и Вильгельм Гримм");

        Set<Author> authorList1 = new HashSet<>();
        authorList1.add(author1);
        authorList1.add(author2);

        Set<Book> booksActual1 = new HashSet<>();
        booksActual1.add(new Book("XXX-XXX-XXX", "Сказки Шарля Перро", authorList1, new HashSet<>()));

        Set<Book> booksExpected1 = bookRepository.getAllByAuthor("Перро");
        Set<Book> booksExpected2 = bookRepository.getAllByAuthor("Андерсен");

        assertEquals(booksExpected1, booksActual1);
        assertEquals(booksExpected2, new HashSet<>());
    }

    @DisplayName("Получение книг по жанру")
    @Test
    @Sql(statements = {"INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика'), (2, 'Мистика'), (3, 'Сказки')",
            "INSERT INTO BOOKS (ISBN, TITLE) values ('XXX-XXX-XXX', 'Сказки Шарля Перро')",
            "INSERT INTO BOOK_GENRES (ISBN, GENRE_ID) values ('XXX-XXX-XXX', 1), ('XXX-XXX-XXX', 2)"})
    void getAllByGenre() {
        Genre genre1 = new Genre(1L, "Фантастика");
        Genre genre2 = new Genre(2L, "Мистика");

        Set<Genre> genres = new HashSet<>();
        genres.add(genre1);
        genres.add(genre2);

        Set<Book> booksActual = new HashSet<>();
        booksActual.add(new Book("XXX-XXX-XXX", "Сказки Шарля Перро", new HashSet<>(), genres));

        Set<Book> booksExpected1 = bookRepository.getAllByGenre("Фантастика");
        Set<Book> booksExpected2 = bookRepository.getAllByGenre("Сказки");

        assertEquals(booksExpected1, booksActual);
        assertEquals(booksExpected2, new HashSet<>());
    }
}
