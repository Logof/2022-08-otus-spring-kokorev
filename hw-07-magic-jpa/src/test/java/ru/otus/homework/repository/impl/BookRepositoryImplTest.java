package ru.otus.homework.repository.impl;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.service.impl.OutputServiceStreams;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тест BookDao")
@DataJpaTest
@ComponentScan("ru.otus.homework")
@Transactional
public class BookRepositoryImplTest {

    @MockBean
    private OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void clearData() {
        entityManager.clear();
    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(bookActual);

        Book bookExpected = bookRepository.getBookByIsbn(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(bookActual);
        bookActual.setTitle("Test Title");
        Book bookExpected = bookRepository.update(bookActual);
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test1");
        Book bookExpected = new Book("YYY-Y-YYY-YYYYY-Y", "Test2");
        bookRepository.insert(bookActual);
        bookRepository.insert(bookExpected);

        bookRepository.deleteByIsbn(bookActual.getIsbn());

        boolean bookActualRecordFound = false;
        boolean bookExpectedRecordFound = false;

        for (Book book : bookRepository.getAll()) {
            if (book.getTitle().equals(bookActual.getTitle())) {
                bookActualRecordFound = true;
            }
            if (book.getTitle().equals(bookExpected.getTitle())) {
                bookExpectedRecordFound = true;
            }
        }
        assertFalse(bookActualRecordFound);
        assertTrue(bookExpectedRecordFound);
    }

    @DisplayName("Получение всех записей")
    @Test
    void getAllTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(bookActual);

        List<Book> booksExpectedList = bookRepository.getAll();

        assertThat(booksExpectedList, IsIterableContainingInOrder.contains(Collections.singletonList(bookActual).toArray()));
    }

    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(bookActual);

        Book bookExpected = bookRepository.getBookByIsbn(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }


    @DisplayName("Получение книг по автору")
    @Test
    void getAllByAuthorTest() {
        Author author1 = entityManager.persist(new Author("Author 1"));
        Author author2 = entityManager.persist(new Author("Author 2"));

        List<Author> authorList1 = new ArrayList<>();
        authorList1.add(author1);
        authorList1.add(author2);

        Book book1 = new Book("XXX-XXX-1", "Title 1", authorList1, new ArrayList<>(), new ArrayList<>());
        bookRepository.insert(book1);

        List<Book> booksActual1 = new ArrayList<>();
        booksActual1.add(book1);

        List<Book> booksExpected1 = bookRepository.getAllByAuthor("Author 1");
        List<Book> booksExpected2 = bookRepository.getAllByAuthor("Author 3");

        assertEquals(booksExpected1, booksActual1);
        assertEquals(booksExpected2, new ArrayList<>());
    }

    @DisplayName("Получение книг по жанру")
    @Test
    void getAllByGenre() {
        Genre genre1 = entityManager.persist(new Genre("Genre 1"));
        Genre genre2 = entityManager.persist(new Genre("Genre 2"));

        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        Book book = new Book("XXX-XXX-1", "Title 1", new ArrayList<>(), genres, new ArrayList<>());
        bookRepository.insert(book);

        List<Book> booksActual = new ArrayList<>();
        booksActual.add(book);

        List<Book> booksExpected1 = bookRepository.getAllByGenre("Genre 1");
        List<Book> booksExpected2 = bookRepository.getAllByGenre("Genre 3");

        assertEquals(booksExpected1, booksActual);
        assertEquals(booksExpected2, new ArrayList<>());
    }
}
