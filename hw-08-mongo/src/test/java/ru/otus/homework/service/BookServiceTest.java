package ru.otus.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.exception.FieldRequiredException;
import ru.otus.homework.service.impl.OutputServiceStreams;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса по работе с книгами")
@DataMongoTest
@ComponentScan("ru.otus.homework")
public class BookServiceTest {

    @MockBean
    private OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookService bookService;

    private void clearTestingData() {
        try {
            bookService.deleteByIsbn("TEST-ISBN-0");
        } catch (DataNotFountException ex) {
            //Все хорошо
        }
        try {
            bookService.deleteByIsbn("TEST-ISBN-1");
        } catch (DataNotFountException ex) {
            //Все хорошо
        }
        try {
            bookService.deleteByIsbn("TEST-ISBN-2");
        } catch (DataNotFountException ex) {
            //Все хорошо
        }
        try {
            bookService.deleteByIsbn("TEST-ISBN-3");
        } catch (DataNotFountException ex) {
            //Все хорошо
        }
        try {
            bookService.deleteByIsbn("TEST-ISBN-4");
        } catch (DataNotFountException ex) {
            //Все хорошо
        }
        try {
            bookService.deleteByIsbn("TEST-ISBN-5");
        } catch (DataNotFountException ex) {
            //Все хорошо
        }
        reset(outputServiceStreams);
    }

    @Test
    public void updateTest() {
        Book book = new Book("TEST-ISBN-0", "test title 0", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        bookService.save(book);
        reset(outputServiceStreams);
        book.setTitle("New title 0");
        bookService.save(book);
        verify(outputServiceStreams).outString("Book saved. ISBN: TEST-ISBN-0");
        reset(outputServiceStreams);
        book.setTitle(null);
        Exception exceptionNull = assertThrows(FieldRequiredException.class, () -> {
            bookService.save(book);
        });
        String expectedMessage = "Required field(s): isbn, title";
        String actualMessageNull = exceptionNull.getMessage();
        assertTrue(actualMessageNull.contains(expectedMessage));

        reset(outputServiceStreams);
        book.setTitle("");
        Exception exceptionEmpty = assertThrows(FieldRequiredException.class, () -> {
            bookService.save(book);
        });
        String actualMessageEmpty = exceptionEmpty.getMessage();
        assertTrue(actualMessageEmpty.contains(expectedMessage));
        reset(outputServiceStreams);

        clearTestingData();
    }

    @Test
    public void addTest() {
        clearTestingData();

        List<String> authorList = new ArrayList<>();
        authorList.add("Автор Тест");
        authorList.add("Автор Тест 2");
        List<String> genreList = new ArrayList<>();
        genreList.add("Жанр");

        Book book = new Book("TEST-ISBN-0", "New title 0", authorList, genreList, new ArrayList<>());
        bookService.save(book);
        verify(outputServiceStreams).outString(String.format("Book saved. ISBN: %s", book.getIsbn()));

        clearTestingData();
    }

    @Test
    void deleteByIdTest() {
        clearTestingData();

        Book book = new Book("TEST-ISBN-0", "test title 0");
        bookService.save(book);
        bookService.deleteByIsbn(book.getIsbn());
        verify(outputServiceStreams).outString("Book deleted. ID: TEST-ISBN-0");

        clearTestingData();
    }

    @Test
    void getAllTest() {
        clearTestingData();

        bookService.getAll();
        verify(outputServiceStreams).outString("Total books: 0\n");

        List<Book> books = new ArrayList<>();
        books.add(new Book("TEST-ISBN-0", "test title 0"));
        books.add(new Book("TEST-ISBN-1", "test title 1"));
        books.add(new Book("TEST-ISBN-2", "test title 2"));
        books.add(new Book("TEST-ISBN-3", "test title 3"));
        books.add(new Book("TEST-ISBN-4", "test title 4"));
        books.add(new Book("TEST-ISBN-5", "test title 5"));

        for (Book book : books) {
            bookService.save(book);
        }

        bookService.getAll();
        verify(outputServiceStreams).outString("Total books: 6" + System.lineSeparator() +
                "Title: test title 0 (ISBN: TEST-ISBN-0)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 1 (ISBN: TEST-ISBN-1)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 2 (ISBN: TEST-ISBN-2)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 3 (ISBN: TEST-ISBN-3)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 4 (ISBN: TEST-ISBN-4)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: test title 5 (ISBN: TEST-ISBN-5)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());

        clearTestingData();
    }

    @Test
    void getByIdTest() {
        clearTestingData();

        bookService.save(new Book("TEST-ISBN-4", "test title 4"));
        bookService.getByIsbn("TEST-ISBN-4");
        verify(outputServiceStreams).outString("Title: test title 4 (ISBN: TEST-ISBN-4)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator());

        clearTestingData();
    }
}
