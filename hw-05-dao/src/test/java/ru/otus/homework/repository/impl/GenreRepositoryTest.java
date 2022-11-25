package ru.otus.homework.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.repository.GenreRepository;
import ru.otus.homework.hw05.repository.impl.GenreRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест GenreDao")
@JdbcTest
@Import(GenreRepositoryImpl.class)
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
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.insert(genreActual);
        assertNotNull(genreActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.insert(genreActual);
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
        booksActualList.add(new Genre(null, "Test record"));

        for (Genre genre : booksActualList) {
            genreRepository.insert(genre);
        }

        List<Genre> booksExpectedList = genreRepository.getAll();
        assertEquals(booksExpectedList, booksActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual= genreRepository.insert(genreActual);
        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Проверка, что жанр ассоциирован с книгой")
    @Test
    void isAttachedToBookTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.insert(genreActual);

        assertFalse(genreRepository.genreHasBooks(genreActual.getId()));

        jdbc.update("INSERT INTO books (ISBN, Title) VALUES ('TEST_BOOK', 'Title')", Map.of());
        jdbc.update("INSERT INTO book_genres (ISBN, GENRE_ID) VALUES ('TEST_BOOK', :id)", Map.of("id", genreActual.getId()));

        assertTrue(genreRepository.genreHasBooks(genreActual.getId()));
    }

    @DisplayName("Получение записи по имени")
    @Test
    void getByFullNameTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.insert(genreActual);

        Genre genreExpected = genreRepository.getGenreByName(genreActual.getGenreName());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Получение записи по ID")
    @Test
    void getAuthorByIdTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.insert(genreActual);

        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }


}
