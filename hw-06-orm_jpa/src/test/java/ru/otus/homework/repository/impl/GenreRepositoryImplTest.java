package ru.otus.homework.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.homework.hw06.entity.Genre;
import ru.otus.homework.hw06.repository.GenreRepository;
import ru.otus.homework.hw06.repository.impl.GenreRepositoryImpl;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест GenreDao")
@DataJpaTest
@Import(GenreRepositoryImpl.class)
public class GenreRepositoryImplTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    @Sql(statements = "DELETE FROM genres")
    void prepare() {

    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Genre genreActual = new Genre("Test record");
        genreActual = genreRepository.save(genreActual);
        assertNotNull(genreActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    @Sql(statements = "INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика')")
    void updateTest() {
        Genre genreActual = genreRepository.getGenreById(1L);
        genreActual.setGenreName("Мистика");
        Genre genreExpected = genreRepository.save(genreActual);
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Удаление")
    @Test
    @Sql(statements = "INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика'), (2, 'Мистика')")
    void deleteTest() {
        Genre genre1 = genreRepository.getGenreById(1L);
        Genre genre2 = genreRepository.getGenreById(2L);

        genreRepository.deleteById(1L);

        boolean genre1RecordFound = false;
        boolean genre2RecordFound = false;

        for (Genre genre : genreRepository.getAll()) {
            if (genre.equals(genre1)) {
                genre1RecordFound = true;
            }
            if (genre.equals(genre2)) {
                genre2RecordFound = true;
            }
        }
        assertFalse(genre1RecordFound);
        assertTrue(genre2RecordFound);
    }

    @DisplayName("Получение всех записей")
    @Test
    @Sql(statements = "INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика'), (2, 'Мистика'), (3, 'Сказки')")
    void getAllTest() {
        Set<Genre> genreActualList = new HashSet<>();
        genreActualList.add(new Genre(1L, "Фантастика"));
        genreActualList.add(new Genre(2L, "Мистика"));
        genreActualList.add(new Genre(3L, "Сказки"));

        assertEquals(genreRepository.getAll(), genreActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    @Sql(statements = "INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика'), (2, 'Мистика'), (3, 'Сказки')")
    void getByIdTest() {
        Genre genreActual = new Genre(1L, "Фантастика");
        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Проверка, что жанр ассоциирован с книгой")
    @Test
    @Sql(statements = {"INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика'), (2, 'Мистика')",
            "INSERT INTO BOOKS (ISBN, TITLE) values ('XXX-XXX-XXX', 'Сказки Шарля Перро')",
            "INSERT INTO BOOK_GENRES (ISBN, GENRE_ID) values ('XXX-XXX-XXX', 1)"})
    void isAttachedToBookTest() {
        assertTrue(genreRepository.genreHasBooks(1));
        assertFalse(genreRepository.genreHasBooks(2));
    }

    @DisplayName("Получение записи по имени")
    @Test
    @Sql(statements = {"INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика'), (2, 'Мистика')"})
    void getByGenreNameTest() {
        Genre genreActual = new Genre(1L, "Фантастика");
        Genre genreExpected = genreRepository.getGenreByName("Фантастика");
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Получение записи по ID")
    @Test
    @Sql(statements = {"INSERT INTO GENRES(id, genre_name) values (1, 'Фантастика'), (2, 'Мистика')"})
    void getAuthorByIdTest() {
        Genre genreActual = new Genre(1L, "Фантастика");
        Genre genreExpected = genreRepository.getGenreById(1L);
        assertEquals(genreExpected, genreActual);
    }


}
