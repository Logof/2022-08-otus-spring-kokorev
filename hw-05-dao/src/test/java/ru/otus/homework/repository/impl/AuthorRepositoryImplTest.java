package ru.otus.homework.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.entity.Author;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест AuthorDao")
@JdbcTest
@ComponentScan(value = "ru.otus.homework.repository.impl")
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @MockBean
    private BookRepository bookRepository;

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Author authorActual = new Author(null, "Test record");
        authorActual = authorRepository.insert(authorActual);
        assertNotNull(authorActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Author authorActual = new Author(null, "Test record");
        authorActual = authorRepository.insert(authorActual);

        authorActual.setFullName("Test Title");
        Integer countUpdateRow = authorRepository.update(authorActual);
        assertEquals(countUpdateRow, 1);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Author authorActual = new Author(1L, "Test1");
        Author authorExpected = new Author(2L, "Test2");
        authorRepository.insert(authorActual);
        authorRepository.insert(authorExpected);

        authorRepository.delete(authorActual.getId());

        boolean authorActualRecordFound = false;
        boolean authorExpectedRecordFound = false;

        for (Author author : authorRepository.getAll()) {
            if (author.getFullName().equals(authorActual.getFullName())) {
                authorActualRecordFound = true;
            }
            if (author.getFullName().equals(authorExpected.getFullName())) {
                authorExpectedRecordFound = true;
            }
        }
        assertFalse(authorActualRecordFound);
        assertTrue(authorExpectedRecordFound);
    }

    @DisplayName("Получение всех записей")
    @Test
    void getAllTest() {
        List<Author> authorsActualList = new ArrayList<>();
        Author authorActual = new Author(1L, "Test record");
        authorsActualList.add(authorActual);

        authorRepository.insert(authorActual);
        List<Author> authorsExpectedList = authorRepository.getAll();
        assertEquals(authorsExpectedList, authorsActualList);
    }


    @DisplayName("Получение записи по ID")
    @Test
    void getAuthorByIdTest() {
        Author authorActual = new Author(null, "Test record");
        authorActual = authorRepository.insert(authorActual);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Получение записи по имени")
    @Test
    void getByFullNameTest() {
        Author authorActual = new Author(null, "Test record");
        authorActual = authorRepository.insert(authorActual);

        Author authorExpected = authorRepository.getByFullName(authorActual.getFullName());
        assertEquals(authorExpected, authorActual);

    }

    @DisplayName("Проверка, что автор ассоциирован с книгой")
    @Test
    void isAttachedToBookTest() {
        Author authorActual = new Author(null, "Test record");
        authorActual = authorRepository.insert(authorActual);
        assertFalse(authorRepository.authorHasBooks(authorActual.getId()));

        jdbc.update("INSERT INTO books (ISBN, Title) VALUES ('TEST_BOOK', 'Title')", Map.of());
        jdbc.update("INSERT INTO book_authors (ISBN, AUTHOR_ID) VALUES ('TEST_BOOK', :id)", Map.of("id", authorActual.getId()));

        assertTrue(authorRepository.authorHasBooks(authorActual.getId()));
    }


}
