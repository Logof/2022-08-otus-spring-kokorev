package ru.otus.homework.repository.impl;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест AuthorDao")
@DataJpaTest
@ComponentScan("ru.otus.homework")
@Transactional
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    TestEntityManager entityManager;

    @DisplayName("Добавление")
    @Test
    @Transactional
    void insertTest() {
        Author authorActual = new Author("Test record");
        authorActual = authorRepository.save(authorActual);
        assertNotNull(authorActual.getId());
    }

    @DisplayName("Обновление")
    @Test
    @Transactional
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
    @Transactional
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
    @Transactional
    void getAllTest() {
        List<Author> booksActualList = new ArrayList<>();
        booksActualList.add(new Author("Test record"));

        for (Author genre : booksActualList) {
            authorRepository.save(genre);
        }

        Set<Author> authorExpectedList = authorRepository.getAll();
        assertEquals(authorExpectedList, booksActualList);
    }


    @DisplayName("Получение записи по ID")
    @Test
    @Transactional
    void getAuthorByIdTest() {
        Author authorActual = new Author("Test record");
        authorActual = authorRepository.save(authorActual);

        Author authorExpected = authorRepository.getAuthorById(authorActual.getId());
        assertEquals(authorExpected, authorActual);
    }

    @DisplayName("Получение записи по имени")
    @Test
    @Transactional
    void getByFullNameTest() {
        Author authorActual = authorRepository.save(new Author("Test record"));

        Author authorExpected = authorRepository.getByFullName(authorActual.getFullName());
        assertEquals(authorExpected, authorActual);

    }

    @DisplayName("Проверка, что автор ассоциирован с книгой")
    @Test
    @Transactional
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
    @Transactional
    void getAuthorsByIsbnTest() {
        Set<Author> authorActualList = new HashSet<>();
        authorActualList.add(new Author(null, "Test record"));

        Book book = new Book("TEST_BOOK_ASSOC", "TEST TITLE",
                authorActualList, new HashSet<>(), new HashSet<>());
        entityManager.persist(book);

        Set<Author> authorExpected = authorRepository.getAuthorsByIsbn("TEST_BOOK_ASSOC");
        assertFalse(authorExpected.isEmpty());
        assertThat(authorExpected, IsIterableContainingInOrder.contains(authorActualList.toArray()));
    }
}
