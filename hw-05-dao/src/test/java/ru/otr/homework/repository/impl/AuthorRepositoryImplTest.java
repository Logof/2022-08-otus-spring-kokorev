package ru.otr.homework.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.entity.Author;
import ru.otus.homework.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест AuthorDao")
@SpringBootTest(classes = Application.class)
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    public void clearData() {
        jdbc.update("DELETE authors", Map.of());
    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Author authorActual = new Author(1L, "Test record");
        int countInsertRow = authorRepository.insert(authorActual);
        assertEquals(countInsertRow, 1);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Author authorActual = new Author(1L, "Test record");
        authorRepository.insert(authorActual);
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


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Author authorActual = new Author(1L, "Test record");
        authorRepository.insert(authorActual);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);

        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Количество")
    @Test
    void countTest() {
        long beginRecordCount = authorRepository.count();
        Author author = new Author(1L, "Test record");
        authorRepository.insert(author);
        assertTrue(authorRepository.count() >= 1 && beginRecordCount < authorRepository.count());
    }
}
