package ru.otr.homework.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест GenreDao")
@SpringBootTest(classes = Application.class)
public class GenreDaoTest {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    private void clearData() {
        jdbc.update("DELETE genres", Map.of());
    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Genre genreActual = new Genre(1, "Test record");
        int countInsertRow = genreDao.insert(genreActual);
        assertEquals(countInsertRow, 1);

        Genre genreExpected = genreDao.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Genre genreActual = new Genre(1, "Test record");
        genreDao.insert(genreActual);
        genreActual.setGenreName("Test Title");
        int countUpdateRow = genreDao.update(genreActual);
        assertEquals(countUpdateRow, 1);

        Genre genreExpected = genreDao.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Genre genreActual = new Genre(1, "Test1");
        Genre genreExpected = new Genre(2, "Test2");
        genreDao.insert(genreActual);
        genreDao.insert(genreExpected);

        genreDao.delete(genreActual.getId());

        boolean genreActualRecordFound = false;
        boolean genreExpectedRecordFound = false;

        for (Genre book : genreDao.getAll()) {
            if (book.getGenreName().equals(genreActual.getGenreName())) {
                genreActualRecordFound = true;
            }
            if (book.getGenreName().equals(genreExpected.getGenreName())) {
                genreExpectedRecordFound = true;
            }
        }
        assertFalse(genreActualRecordFound);
        assertTrue(genreExpectedRecordFound);
    }

    @DisplayName("Получение всех записей")
    @Test
    void getAllTest() {
        List<Genre> booksActualList = new ArrayList<>();
        Genre genreActual = new Genre(1, "Test record");
        booksActualList.add(genreActual);

        genreDao.insert(genreActual);
        List<Genre> booksExpectedList = genreDao.getAll();
        assertEquals(booksExpectedList, booksActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Genre genreActual = new Genre(1, "Test record");
        genreDao.insert(genreActual);

        Genre genreExpected = genreDao.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);

        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Количество")
    @Test
    void countTest() {
        long beginRecordCount = genreDao.count();
        Genre genre = new Genre(1, "Test record");
        genreDao.insert(genre);
        assertTrue(genreDao.count() >= 1 && beginRecordCount < genreDao.count());
    }

    @DisplayName("Генерация ID")
    @Test
    void generateIdTest() {
        long beginRecordCount = genreDao.count();
        genreDao.generateId();
        assertTrue(genreDao.generateId() == beginRecordCount + 1);
    }

}
