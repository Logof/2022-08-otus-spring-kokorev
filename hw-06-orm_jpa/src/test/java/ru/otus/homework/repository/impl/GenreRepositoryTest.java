package ru.otus.homework.repository.impl;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.repository.GenreRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест GenreDao")
@DataJpaTest
@ComponentScan("ru.otus.homework.repository.impl")
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);
        assertNotNull(genreActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);
        genreActual.setGenreName("Test Title");

        Genre genreExpected = genreRepository.save(genreActual);
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Genre genreActual = genreRepository.save(new Genre("Test1"));
        Genre genreExpected = genreRepository.save(new Genre("Test2"));

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
        Set<Genre> booksActualList = new HashSet<>();
        booksActualList.add(new Genre("Test record"));

        for (Genre genre : booksActualList) {
            genreRepository.save(genre);
        }

        Set<Genre> booksExpectedList = genreRepository.getAll();
        assertEquals(booksExpectedList, booksActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);
        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Проверка, что жанр ассоциирован с книгой")
    @Test
    void isAttachedToBookTest() {
        Genre genreActual = genreRepository.save(new Genre("Test record"));
        assertFalse(genreRepository.isAttachedToBook(genreActual.getId()));

        Book book = new Book("XXX-XXX-XXXX-XX", "Test", new HashSet<>(),
                new HashSet<>(Collections.singletonList(genreActual)), new HashSet<>());
        entityManager.persist(book);

        assertTrue(genreRepository.isAttachedToBook(genreActual.getId()));
    }

    @DisplayName("Получение записи по имени")
    @Test
    void getByGenreNameTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);

        Genre genreExpected = genreRepository.getGenreByName(genreActual.getGenreName());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Получение записи по ID")
    @Test
    void getAuthorByIdTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);

        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Получение списка авторов по ISBN книги")
    @Test
    void getGenresByIsbnTest() {
        Genre actualGenre = genreRepository.save(new Genre("Test record"));

        Book book = new Book("TEST_BOOK_ASSOC", "TEST TITLE", new HashSet<>(),
                new HashSet<>(Collections.singletonList(actualGenre)), new HashSet<>());
        entityManager.persist(book);

        Set<Genre> genresExpected = genreRepository.getGenresByIsbn("TEST_BOOK_ASSOC");
        assertFalse(genresExpected.isEmpty());
        assertEquals(genresExpected.size(), Collections.singletonList(actualGenre).size());
        assertThat(genresExpected, IsIterableContainingInOrder.contains(Collections.singletonList(actualGenre).toArray()));
    }
}
