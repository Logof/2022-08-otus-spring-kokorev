package ru.otr.homework.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест GenreDao")
@SpringBootTest(classes = Application.class)
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    public void clearData() {
        jdbc.update("DELETE genres", Map.of());
    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Genre genreActual = new Genre(1L, "Test record");
        Genre genreExpected = genreRepository.insert(genreActual);
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Genre genreActual = new Genre(1L, "Test record");
        genreRepository.insert(genreActual);
        genreActual.setGenreName("Test Title");
        int countUpdateRow = genreRepository.update(genreActual);
        assertEquals(countUpdateRow, 1);

        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Genre genreActual = new Genre(1L, "Test1");
        Genre genreExpected = new Genre(2L, "Test2");
        genreRepository.insert(genreActual);
        genreRepository.insert(genreExpected);

        genreRepository.deleteById(genreActual.getId());

        boolean genreActualRecordFound = false;
        boolean genreExpectedRecordFound = false;

        for (Genre book : genreRepository.getAll()) {
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
        Genre genreActual = new Genre(1L, "Test record");
        booksActualList.add(genreActual);

        genreRepository.insert(genreActual);
        List<Genre> booksExpectedList = genreRepository.getAll();
        assertEquals(booksExpectedList, booksActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Genre genreActual = new Genre(1L, "Test record");
        Genre genreExpected = genreRepository.insert(genreActual);
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Количество")
    @Test
    void countTest() {
        long beginRecordCount = genreRepository.count();
        Genre genre = new Genre(1L, "Test record");
        genreRepository.insert(genre);
        assertTrue(genreRepository.count() >= 1 && beginRecordCount < genreRepository.count());
    }

    @DisplayName("Генерация ID")
    @Test
    void generateIdTest() {
        long beginRecordCount = genreRepository.count();
        genreRepository.generateId();
        assertEquals(genreRepository.generateId(), beginRecordCount + 1);
    }

}