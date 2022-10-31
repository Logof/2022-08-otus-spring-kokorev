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
import ru.otus.homework.repository.AuthorRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест AuthorDao")
@DataJpaTest
@Import(AuthorRepositoryImpl.class)
public class AuthorRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    @Sql(statements = "DELETE FROM authors")
    void prepare() {

    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Author authorActual = new Author("Test record");
        authorActual = authorRepository.save(authorActual);
        assertNotNull(authorActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    @Sql(statements = "INSERT INTO AUTHORS(id, full_name) values (1, 'Шарль Перро')")
    void updateTest() {
        Author authorActual = authorRepository.getAuthorById(1);
        authorActual.setFullName("Test Title");

        Author savedAuthorExpected = authorRepository.save(authorActual);
        assertEquals(savedAuthorExpected, authorActual);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Удаление")
    @Test
    @Sql(statements = "INSERT INTO AUTHORS(id, full_name) values (1, 'Шарль Перро'), (2, 'Якоб и Вильгельм Гримм')")
    void deleteTest() {
        Author author1 = authorRepository.getAuthorById(1);
        Author author2 = authorRepository.getAuthorById(2);

        authorRepository.delete(1);

        Boolean author1RecordFound = false;
        Boolean author2RecordFound = false;

        for (Author author : authorRepository.getAll()) {
            if (author.equals(author1)) {
                author1RecordFound = true;
            }
            if (author.equals(author2)) {
                author2RecordFound = true;
            }
        }
        assertFalse(author1RecordFound);
        assertTrue(author2RecordFound);
    }

    @DisplayName("Получение всех записей")
    @Test
    @Sql(statements = "INSERT INTO AUTHORS(id, full_name) values (1, 'Шарль Перро'), " +
            "(2, 'Якоб и Вильгельм Гримм'), (3, 'Андерсен Ханс Кристиан')")
    void getAllTest() {
        Set<Author> booksActualList = new HashSet<>();
        booksActualList.add(new Author(1L, "Шарль Перро"));
        booksActualList.add(new Author(2L, "Якоб и Вильгельм Гримм"));
        booksActualList.add(new Author(3L, "Андерсен Ханс Кристиан"));

        Set<Author> authorExpectedList = authorRepository.getAll();
        assertEquals(authorExpectedList, booksActualList);
    }


    @DisplayName("Получение записи по ID")
    @Test
    @Sql(statements = "INSERT INTO AUTHORS(id, full_name) values (1, 'Шарль Перро'), " +
            "(2, 'Якоб и Вильгельм Гримм'), (3, 'Андерсен Ханс Кристиан')")
    void getAuthorByIdTest() {
        Author authorActual = new Author(1L, "Шарль Перро");
        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Получение записи по имени")
    @Test
    @Sql(statements = "INSERT INTO AUTHORS(id, full_name) values (1, 'Шарль Перро'), " +
            "(2, 'Якоб и Вильгельм Гримм'), (3, 'Андерсен Ханс Кристиан')")
    void getByFullNameTest() {
        Author authorActual = new Author(2L, "Якоб и Вильгельм Гримм");

        Author authorExpected = authorRepository.getByFullName("Гримм");
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Проверка, что автор ассоциирован с книгой")
    @Test
    @Sql(statements = {"INSERT INTO AUTHORS(id, full_name) values (1, 'Шарль Перро'), (2, 'Якоб и Вильгельм Гримм')",
            "INSERT INTO BOOKS (ISBN, TITLE) values ('XXX-XXX-XXX', 'Сказки Шарля Перро')",
            "INSERT INTO BOOK_AUTHORS (ISBN, AUTHOR_ID) values ('XXX-XXX-XXX', 1)"})
    void isAttachedToBookTest() {
        assertTrue(authorRepository.authorHasBooks(1));
        assertFalse(authorRepository.authorHasBooks(2));
    }


}
