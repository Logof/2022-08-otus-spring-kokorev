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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест GenreDao")
@DataJpaTest
@ComponentScan("ru.otus.homework")
@Transactional
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Добавление")
    @Test
    @Transactional
    void insertTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);
        assertNotNull(genreActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    @Transactional
    void updateTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);
        genreActual.setGenreName("Test Title");

        Genre genreExpected = genreRepository.save(genreActual);
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Удаление")
    @Test
    @Transactional
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
    @Transactional
    void getAllTest() {
        List<Genre> booksActualList = new ArrayList<>();
        booksActualList.add(new Genre("Test record"));

        for (Genre genre : booksActualList) {
            genreRepository.save(genre);
        }

        List<Genre> booksExpectedList = genreRepository.getAll();
        assertEquals(booksExpectedList, booksActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    @Transactional
    void getByIdTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);
        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Проверка, что жанр ассоциирован с книгой")
    @Test
    @Transactional
    void isAttachedToBookTest() {
        Genre genreActual = genreRepository.save(new Genre("Test record"));
        assertFalse(genreRepository.isAttachedToBook(genreActual.getId()));

        Book book = new Book("XXX-XXX-XXXX-XX", "Test", new ArrayList<>(),
                Collections.singletonList(genreActual), new ArrayList<>());
        entityManager.persist(book);

        assertTrue(genreRepository.isAttachedToBook(genreActual.getId()));
    }

    @DisplayName("Получение записи по имени")
    @Test
    @Transactional
    void getByGenreNameTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);

        Genre genreExpected = genreRepository.getGenreByName(genreActual.getGenreName());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Получение записи по ID")
    @Test
    @Transactional
    void getAuthorByIdTest() {
        Genre genreActual = new Genre(null, "Test record");
        genreActual = genreRepository.save(genreActual);

        Genre genreExpected = genreRepository.getGenreById(genreActual.getId());
        assertEquals(genreExpected, genreActual);
    }

    @DisplayName("Получение списка авторов по ISBN книги")
    @Test
    @Transactional
    void getGenresByIsbnTest() {
        Genre actualGenre = genreRepository.save(new Genre("Test record"));

        Book book = new Book("TEST_BOOK_ASSOC", "TEST TITLE", new ArrayList<>(),
                Collections.singletonList(actualGenre), new ArrayList<>());
        entityManager.persist(book);

        List<Genre> genresExpected = genreRepository.getGenresByIsbn("TEST_BOOK_ASSOC");
        assertFalse(genresExpected.isEmpty());
        assertEquals(genresExpected.size(), Collections.singletonList(actualGenre).size());
        assertThat(genresExpected, IsIterableContainingInOrder.contains(Collections.singletonList(actualGenre).toArray()));
    }
}
