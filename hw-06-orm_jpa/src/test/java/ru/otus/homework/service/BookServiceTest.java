package ru.otus.homework.service;

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
import ru.otus.homework.exception.FieldRequiredException;
import ru.otus.homework.service.impl.OutputServiceStreams;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса по работе с книгами")
@DataJpaTest
@ComponentScan("ru.otus.homework")
public class BookServiceTest {

    @MockBean
    private OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookService bookService;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @Transactional
    public void updateTitleTest() {
        Book book = new Book("XXX-X-XXX-XXXXX-0", "test title 0", new HashSet<>(), new HashSet<>(), new HashSet<>());
        bookService.add(book);
        book.setTitle("New title 0");
        bookService.updateTitle(book);
        Book bookExpected = entityManager.find(Book.class, "XXX-X-XXX-XXXXX-0");
        assertEquals(bookExpected, book);

        book.setTitle(null);
        Exception exceptionNull = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle(book);
        });
        String expectedMessage = "Required field(s): isbn, title";
        String actualMessageNull = exceptionNull.getMessage();
        assertTrue(actualMessageNull.contains(expectedMessage));

        book.setTitle("");
        Exception exceptionEmpty = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle(book);
        });
        String actualMessageEmpty = exceptionEmpty.getMessage();
        assertTrue(actualMessageEmpty.contains(expectedMessage));
    }

    @Test
    @Transactional
    public void addTest() {
        Set<Author> authorList = new HashSet<>();
        authorList.add(new Author("Автор Тест"));
        authorList.add(new Author("Автор Тест 2"));

        Book book = new Book("XXX-X-XXX-XXXXX-0", "New title 0", authorList, new HashSet<>(), new HashSet<>());
        bookService.add(book);
        Book bookExpected = entityManager.find(Book.class, "XXX-X-XXX-XXXXX-0");
        assertEquals(bookExpected, book);
    }

    @Test
    @Transactional
    void deleteByIdTest() {
        Book book = new Book("XXX-X-XXX-XXXXX-0", "test title 0");
        bookService.add(book);
        bookService.deleteByIsbn(book.getIsbn());
        Book bookExpected = entityManager.find(Book.class, "XXX-X-XXX-XXXXX-0");
        assertNull(bookExpected);
    }

    @Test
    @Transactional
    void getAllTest() {
        bookService.getAll();
        Set<Book> books = new HashSet<>();
        books.add(new Book("XXX-X-XXX-XXXXX-0", "test title 0"));
        books.add(new Book("XXX-X-XXX-XXXXX-1", "test title 1"));
        books.add(new Book("XXX-X-XXX-XXXXX-2", "test title 2"));
        books.add(new Book("XXX-X-XXX-XXXXX-3", "test title 3"));
        books.add(new Book("XXX-X-XXX-XXXXX-4", "test title 4"));
        books.add(new Book("XXX-X-XXX-XXXXX-5", "test title 5"));

        for (Book book : books) {
            entityManager.merge(book);
        }

        bookService.getAll();
        verify(outputServiceStreams).outString("Total books: 6" + System.lineSeparator() +
                "Title: test title 0 (ISBN: XXX-X-XXX-XXXXX-0)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 1 (ISBN: XXX-X-XXX-XXXXX-1)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 2 (ISBN: XXX-X-XXX-XXXXX-2)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 3 (ISBN: XXX-X-XXX-XXXXX-3)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 4 (ISBN: XXX-X-XXX-XXXXX-4)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 5 (ISBN: XXX-X-XXX-XXXXX-5)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
    }

    @Test
    @Transactional
    void getByIdTest() {
        entityManager.persist(new Book("XXX-X-XXX-XXXXX-4", "test title 4"));
        bookService.getByIsbn("XXX-X-XXX-XXXXX-4");
        verify(outputServiceStreams).outString("Title: test title 4 (ISBN: XXX-X-XXX-XXXXX-4)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
    }

    @Test
    @Transactional
    void getAllByAuthorTest() {
        Author author1 = entityManager.persist(new Author("Author 1"));
        Author author2 = entityManager.persist(new Author("Author 2"));
        Author author3 = entityManager.persist(new Author("Author 3"));

        Set<Author> authors1 = new HashSet<>();
        authors1.add(author1);

        Set<Author> authors2 = new HashSet<>();
        authors2.add(author2);
        authors2.add(author3);

        Book bookActual1 = new Book("XXX-X-XXX-XXXXX-0", "test title 0", authors1, new HashSet<>(), new HashSet<>());
        Book bookActual2 = new Book("XXX-X-XXX-XXXXX-1", "test title 1", authors2, new HashSet<>(), new HashSet<>());

        entityManager.persist(bookActual1);
        entityManager.persist(bookActual2);

        bookService.getAllByAuthor("Author 2");
        verify(outputServiceStreams).outString("Total books: 1" + System.lineSeparator() +
                "Title: " + bookActual2.getTitle() + " (ISBN: " + bookActual2.getIsbn() + ")" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() +
                "Total authors: 2" + System.lineSeparator() +
                "\tAuthor 2 (id=" + author2.getId() + ")" + System.lineSeparator() +
                "\tAuthor 3 (id=" + author3.getId() + ")" + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
        reset(outputServiceStreams);
        bookService.getAllByAuthor("Author 1");
        verify(outputServiceStreams).outString("Total books: 1" + System.lineSeparator() +
                "Title: " + bookActual1.getTitle() + " (ISBN: " + bookActual1.getIsbn() + ")" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() +
                "Total authors: 1" + System.lineSeparator() +
                "\tAuthor 1 (id=" + author1.getId() + ")" + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
        reset(outputServiceStreams);
    }

    @Test
    @Transactional
    void getAllByGenreTest() {
        Genre genre1 = entityManager.persist(new Genre("Genre 1"));
        Genre genre2 = entityManager.persist(new Genre("Genre 2"));
        Genre genre3 = entityManager.persist(new Genre("Genre 3"));

        Set<Genre> genres1 = new HashSet<>();
        genres1.add(genre1);

        Set<Genre> genres2 = new HashSet<>();
        genres2.add(genre2);
        genres2.add(genre3);

        Book bookActual1 = new Book("XXX-X-XXX-XXXXX-0", "test title 0", new HashSet<>(), genres2, new HashSet<>());
        Book bookActual2 = new Book("XXX-X-XXX-XXXXX-1", "test title 1", new HashSet<>(), genres1, new HashSet<>());

        entityManager.persist(bookActual1);
        entityManager.persist(bookActual2);

        bookService.getAllByGenre("Genre 2");
        verify(outputServiceStreams).outString("Total books: 1" + System.lineSeparator() +
                "Title: " + bookActual1.getTitle() + " (ISBN: " + bookActual1.getIsbn() + ")" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() +
                "Total genres: 2" + System.lineSeparator() +
                "\t" + genre2.getGenreName() + " (id=" + genre2.getId() + ")" + System.lineSeparator() +
                "\t" + genre3.getGenreName() + " (id=" + genre3.getId() + ")" + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
        reset(outputServiceStreams);
        bookService.getAllByGenre("Genre 1");
        verify(outputServiceStreams).outString("Total books: 1" + System.lineSeparator() +
                "Title: " + bookActual2.getTitle() + " (ISBN: " + bookActual2.getIsbn() + ")" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() +
                "Total genres: 1" + System.lineSeparator() +
                "\t" + genre1.getGenreName() + " (id=" + genre1.getId() + ")" + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
        reset(outputServiceStreams);
    }

    @Test
    @Transactional
    void addGenreToBook() {
        Genre genre = entityManager.persist(new Genre("Genre 1"));
        Book book = entityManager.persist(new Book("XXX-X-XXX-XXXXX-0", "Title", new HashSet<>(),
                new HashSet<>(), new HashSet<>()));

        bookService.addGenreToBook("XXX-X-XXX-XXXXX-0", "Genre 1");
        bookService.getByIsbn("XXX-X-XXX-XXXXX-0");
        verify(outputServiceStreams).outString(
                "Title: " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")" + System.lineSeparator() +
                        "Genre: " + System.lineSeparator() +
                        "Total genres: 1" + System.lineSeparator() +
                        "\t" + genre.getGenreName() + " (id=" + genre.getId() + ")" + System.lineSeparator() +
                        "Authors: " + System.lineSeparator() + System.lineSeparator() +
                        "---------------------------------------" + System.lineSeparator());
        reset(outputServiceStreams);

    }

    @Test
    @Transactional
    void addAuthorToBook() {
        Author author = entityManager.persist(new Author("Author 1"));
        Book book = entityManager.persist(new Book("XXX-X-XXX-XXXXX-0", "Title",
                new HashSet<>(), new HashSet<>(), new HashSet<>()));

        bookService.addAuthorToBook("XXX-X-XXX-XXXXX-0", "Author 1");
        bookService.getByIsbn("XXX-X-XXX-XXXXX-0");
        verify(outputServiceStreams).outString(
                "Title: " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")" + System.lineSeparator() +
                        "Genre: " + System.lineSeparator() + System.lineSeparator() +
                        "Authors: " + System.lineSeparator() +
                        "Total authors: 1" + System.lineSeparator() +
                        "\tAuthor 1 (id=" + author.getId() + ")" + System.lineSeparator() +
                        "---------------------------------------" + System.lineSeparator());
        reset(outputServiceStreams);
    }
}
