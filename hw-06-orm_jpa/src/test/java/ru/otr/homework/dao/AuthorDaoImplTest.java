package ru.otr.homework.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.entity.Author;
import ru.otus.homework.service.impl.OutputServiceStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест AuthorDao")
@SpringBootTest(classes = Application.class)
public class AuthorDaoImplTest {

    @MockBean
    OutputServiceStreams outputServiceStreams;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    public void clearData() {
        jdbc.update("DELETE authors", Map.of());
    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Author authorActual = new Author(1, "Test record");
        int countInsertRow = authorDao.insert(authorActual);
        assertEquals(countInsertRow, 1);

        Optional<Author> authorExpected = authorDao.getAuthorById(authorActual.getId());
        assertTrue(authorExpected.isPresent());
        assertEquals(authorExpected.get(), authorActual);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Author authorActual = new Author(1, "Test record");
        authorDao.insert(authorActual);
        authorActual.setFullName("Test Title");
        Integer countUpdateRow = authorDao.update(authorActual);
        assertEquals(countUpdateRow, 1);

        Optional<Author> authorExpected = authorDao.getAuthorById(authorActual.getId());
        assertTrue(authorExpected.isPresent());
        assertEquals(authorExpected.get(), authorActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Author authorActual = new Author(1, "Test1");
        Author authorExpected = new Author(2, "Test2");
        authorDao.insert(authorActual);
        authorDao.insert(authorExpected);

        authorDao.delete(authorActual.getId());

        boolean authorActualRecordFound = false;
        boolean authorExpectedRecordFound = false;

        for (Author author : authorDao.getAll()) {
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
        Author authorActual = new Author(1, "Test record");
        authorsActualList.add(authorActual);

        authorDao.insert(authorActual);
        List<Author> authorsExpectedList = authorDao.getAll();
        assertEquals(authorsExpectedList, authorsActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Author authorActual = new Author(1, "Test record");
        authorDao.insert(authorActual);

        Optional<Author> authorExpected = authorDao.getAuthorById(authorActual.getId());
        assertTrue(authorExpected.isPresent());
        assertEquals(authorExpected.get(), authorActual);
    }

    @DisplayName("Количество")
    @Test
    void countTest() {
        long beginRecordCount = authorDao.count();
        Author author = new Author(1, "Test record");
        authorDao.insert(author);
        assertTrue(authorDao.count() >= 1 && beginRecordCount < authorDao.count());
    }
}
