package ru.otus.homework.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.entity.Book;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {
    private static final String BOOK_ISBN = "978-5-699-12014-7";

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName(" должен загружать информацию о нужной книге по ее isbn")
    @Test
    void shouldFindExpectedBookById() {


        Optional<Book> bookActual = repositoryJpa.findByIsbn(BOOK_ISBN);
        Book bookExpected = entityManager.find(Book.class, BOOK_ISBN);
        assertThat(bookActual).isPresent().get()
                .usingRecursiveComparison().isEqualTo(bookExpected);
    }
}