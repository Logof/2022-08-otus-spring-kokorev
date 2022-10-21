package ru.otus.homework.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.repository.AuthorRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест AuthorDao")
@DataJpaTest
@ComponentScan("ru.otus.homework.repository.impl")
public class AuthorRepositoryImplTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Author authorActual = new Author("Test record");
        authorActual = authorRepository.save(authorActual);
        assertNotNull(authorActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Author authorActual = new Author(null, "Test record");
        authorActual = authorRepository.save(authorActual);

        authorActual.setFullName("Test Title");
        Author savedAuthorExpected = authorRepository.save(authorActual);
        assertEquals(savedAuthorExpected, authorActual);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Author authorActual = authorRepository.save(new Author("Test1"));
        Author authorExpected = authorRepository.save(new Author("Test2"));

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
        Set<Author> booksActualList = new HashSet<>();
        booksActualList.add(new Author("Test record"));

        for (Author genre : booksActualList) {
            authorRepository.save(genre);
        }

        Set<Author> authorExpectedList = authorRepository.getAll();
        assertEquals(authorExpectedList, booksActualList);
    }


    @DisplayName("Получение записи по ID")
    @Test
    void getAuthorByIdTest() {
        Author authorActual = new Author("Test record");
        authorActual = authorRepository.save(authorActual);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Получение записи по имени")
    @Test
    void getByFullNameTest() {
        Author authorActual = authorRepository.save(new Author("Test record"));

        Author authorExpected = authorRepository.getByFullName(authorActual.getFullName());
        assertEquals(authorExpected, authorActual);

    }

    @DisplayName("Проверка, что автор ассоциирован с книгой")
    @Test
    void isAttachedToBookTest() {
        Author authorActual = authorRepository.save(new Author("Test record"));
        assertFalse(authorRepository.isAttachedToBook(authorActual.getId()));

        Book book = new Book("TEST-ISBN", "Test title",
                new HashSet<>(Collections.singletonList(authorActual)), new HashSet<>(), new HashSet<>());
        entityManager.persist(book);

        assertTrue(authorRepository.isAttachedToBook(authorActual.getId()));
    }

    @DisplayName("Получение списка авторов по ISBN книги")
    @Test
    void getAuthorsByIsbnTest() {
        Set<Author> authorActualList = new HashSet<>();
        authorActualList.add(new Author(null, "Test record"));

        Book book = new Book("TEST_BOOK_ASSOC", "TEST TITLE",
                new HashSet<>(authorActualList), new HashSet<>(), new HashSet<>());
        entityManager.persist(book);

        Set<Author> authorExpected = authorRepository.getAuthorsByIsbn("TEST_BOOK_ASSOC");
        assertFalse(authorExpected.isEmpty());

        assertEquals(authorExpected, authorActualList);
    }
}
