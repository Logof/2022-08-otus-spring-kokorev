package ru.otus.homework.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с жанрами книг")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryJpaTest {
    @Autowired
    private GenreRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Сохранение жанра в БД")
    public void saveTest() {
        Genre actualGenre = new Genre(null, "IT хоррор");
        actualGenre = repositoryJpa.save(actualGenre);
        assertNotEquals(actualGenre.getId(), null);
    }

    @Test
    @DisplayName("Поиск жанра в БД по ID")
    public void findByIdTest() {
        Genre actualGenre = new Genre(null, "IT хоррор");
        actualGenre = repositoryJpa.save(actualGenre);

        Genre expectedGenre = entityManager.find(Genre.class, actualGenre.getId());
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Поиск всех жанров из БД")
    public void findAllTest() {
        List<Genre> actualGenres = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actualGenres.add(repositoryJpa.save(new Genre(null, String.format("Жанр %d", i))));
        }
        List<Genre> expectedGenres = repositoryJpa.findAll();
        assertEquals(expectedGenres, actualGenres);
    }

    @Test
    @DisplayName("Обновление название жанра в БД по ID")
    void updateFullNameByIdTest() {

        Genre actualGenre = new Genre(null, "IT хоррор");
        actualGenre = repositoryJpa.save(actualGenre);

        String newGenreFullName = "Фантастика";

        actualGenre.setName(newGenreFullName);
        repositoryJpa.updateNameById(actualGenre.getId(), newGenreFullName);
        Genre expectedGenre = entityManager.find(Genre.class, actualGenre.getId());
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Удаление жанра из БД по ID")
    void deleteByIdTest() {
        Genre actualGenre = new Genre(null, "IT хоррор");
        actualGenre = repositoryJpa.save(actualGenre);
        Genre expectedGenre = entityManager.find(Genre.class, actualGenre.getId());
        assertEquals(expectedGenre, actualGenre);

        repositoryJpa.deleteById(actualGenre.getId());
        Genre expectedDeletedGenre = entityManager.find(Genre.class, actualGenre.getId());
        assertNull(expectedDeletedGenre);
    }
}
