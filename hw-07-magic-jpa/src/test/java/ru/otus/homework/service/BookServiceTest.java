package ru.otus.homework.service;

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
import ru.otus.homework.exception.FieldRequiredException;
import ru.otus.homework.service.impl.OutputServiceStreams;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса по работе с книгами")
@DataJpaTest
@ComponentScan("ru.otus.homework")
@Transactional
public class BookServiceTest {

    @MockBean
    private OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookService bookService;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void prepareTestingData() {

    }

    @Test
    @Transactional
    public void updateTest() {
        Book book = new Book("XXX-X-XXX-XXXXX-0", "test title 0", new HashSet<>(), new HashSet<>(), new HashSet<>());
        bookService.add(book);
        reset(outputServiceStreams);
        book.setTitle("New title 0");
        bookService.updateTitle(book);
        verify(outputServiceStreams).outString("Updated XXX-X-XXX-XXXXX-0 book");
        reset(outputServiceStreams);
        book.setTitle(null);
        Exception exceptionNull = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle(book);
        });
        String expectedMessage = "Required field(s): isbn, title";
        String actualMessageNull = exceptionNull.getMessage();
        assertTrue(actualMessageNull.contains(expectedMessage));

        reset(outputServiceStreams);
        book.setTitle("");
        Exception exceptionEmpty = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle(book);
        });
        String actualMessageEmpty = exceptionEmpty.getMessage();
        assertTrue(actualMessageEmpty.contains(expectedMessage));
        reset(outputServiceStreams);
    }

    @Test
    public void addTest() {
        Set<Author> authorList = new HashSet<>();
        authorList.add(new Author("Автор Тест"));
        authorList.add(new Author("Автор Тест 2"));
        Set<Genre> genreList = new HashSet<>();
        genreList.add(new Genre("Жанр"));

        Book book = new Book("XXX-X-XXX-XXXXX-0", "New title 0", authorList, genreList, new HashSet<>());
        bookService.add(book);
        verify(outputServiceStreams).outString(String.format("Book added. ISBN: %s", book.getIsbn()));
    }

    @Test
    void deleteByIdTest() {
        Book book = new Book("XXX-X-XXX-XXXXX-0", "test title 0");
        bookService.add(book);
        bookService.deleteByIsbn(book.getIsbn());
        verify(outputServiceStreams).outString("Book deleted. ID: XXX-X-XXX-XXXXX-0");
    }

    @Test
    @Transactional
    void getAllTest() {
        bookService.getAll();
        verify(outputServiceStreams).outString("Total books: 0\n");

        Set<Book> books = new HashSet<>();
        books.add(new Book("XXX-X-XXX-XXXXX-0", "test title 0"));
        books.add(new Book("XXX-X-XXX-XXXXX-1", "test title 1"));
        books.add(new Book("XXX-X-XXX-XXXXX-2", "test title 2"));
        books.add(new Book("XXX-X-XXX-XXXXX-3", "test title 3"));
        books.add(new Book("XXX-X-XXX-XXXXX-4", "test title 4"));
        books.add(new Book("XXX-X-XXX-XXXXX-5", "test title 5"));

        for (Book book : books) {
            bookService.add(book);
            //entityManager.merge(book);
        }

        bookService.getAll();
        verify(outputServiceStreams).outString("Total books: 6" + System.lineSeparator() +
                "Title: test title 0 (ISBN: XXX-X-XXX-XXXXX-0)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "Comments: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 1 (ISBN: XXX-X-XXX-XXXXX-1)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "Comments: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 2 (ISBN: XXX-X-XXX-XXXXX-2)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "Comments: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 3 (ISBN: XXX-X-XXX-XXXXX-3)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "Comments: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 4 (ISBN: XXX-X-XXX-XXXXX-4)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "Comments: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 5 (ISBN: XXX-X-XXX-XXXXX-5)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "Comments: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
    }

    @Test
    void getByIdTest() {
        bookService.add(new Book("XXX-X-XXX-XXXXX-4", "test title 4"));
        bookService.getByIsbn("XXX-X-XXX-XXXXX-4");
        verify(outputServiceStreams).outString("Title: test title 4 (ISBN: XXX-X-XXX-XXXXX-4)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "Comments: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());
    }
}
