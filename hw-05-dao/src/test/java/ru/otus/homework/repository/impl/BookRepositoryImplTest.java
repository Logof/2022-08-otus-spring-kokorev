package ru.otus.homework.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.hw05.entity.Author;
import ru.otus.homework.hw05.entity.Book;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.repository.BookRepository;
import ru.otus.homework.hw05.repository.impl.BookRepositoryImpl;
import ru.otus.homework.hw05.service.impl.OutputServiceStreams;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тест BookDao")
@JdbcTest
@Import(BookRepositoryImpl.class)
public class BookRepositoryImplTest {

    @MockBean
    private OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NamedParameterJdbcOperations jdbc;


    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        int countInsertRow = bookRepository.insert(bookActual);
        assertEquals(countInsertRow, 1);

        Book bookExpected = bookRepository.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(bookActual);
        bookActual.setTitle("Test Title");
        int countUpdateRow = bookRepository.update(bookActual);
        assertEquals(countUpdateRow, 1);

        Book bookExpected = bookRepository.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test1");
        Book bookExpected = new Book("YYY-Y-YYY-YYYYY-Y", "Test2");
        bookRepository.insert(bookActual);
        bookRepository.insert(bookExpected);

        bookRepository.delete(bookActual.getIsbn());

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
        List<Book> booksActualList = new ArrayList<>();
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        booksActualList.add(bookActual);

        bookRepository.insert(bookActual);
        List<Book> booksExpectedList = bookRepository.getAll();
        assertEquals(booksExpectedList, booksActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(bookActual);

        Book bookExpected = bookRepository.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Получение книг по автору")
    @Test
    void getAllByAuthorTest() {
        List<Author> authorList1 = new ArrayList<>();
        authorList1.add(new Author("Author 1"));
        authorList1.add(new Author("Author 2"));

        List<Author> authorList2 = new ArrayList<>();
        authorList2.add(new Author("Author 2"));
        authorList2.add(new Author("Author 3"));

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("XXXXXX1", "Title 1", authorList1, new ArrayList<>()));
        bookList.add(new Book("XXXXXX2", "Title 2", authorList2, new ArrayList<>()));

        for (Book book : bookList) {
            bookRepository.insert(book);
        }

        List<Book> booksActual1 = new ArrayList<>();
        booksActual1.add(bookList.get(0));

        List<Book> booksActual2 = new ArrayList<>();
        booksActual2.add(bookList.get(0));
        booksActual2.add(bookList.get(1));

        List<Book> booksActual3 = new ArrayList<>();
        booksActual3.add(bookList.get(1));

        List<Book> booksExpected1 = bookRepository.getAllByAuthor("Author 1");
        List<Book> booksExpected2 = bookRepository.getAllByAuthor("Author 2");
        List<Book> booksExpected3 = bookRepository.getAllByAuthor("Author 3");

        assertEquals(booksExpected1, booksActual1);
        assertEquals(booksExpected2, booksActual2);
        assertEquals(booksExpected3, booksActual3);
    }

    @DisplayName("Получение книг по жанру")
    @Test
    void getAllByGenre() {
        List<Genre> genreList1 = new ArrayList<>();
        genreList1.add(new Genre("Genre 1"));
        genreList1.add(new Genre("Genre 2"));

        List<Genre> genreList2 = new ArrayList<>();
        genreList2.add(new Genre("Genre 2"));
        genreList2.add(new Genre("Genre 3"));

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("XXXXXX1", "Title 1", new ArrayList<>(), genreList1));
        bookList.add(new Book("XXXXXX2", "Title 2", new ArrayList<>(), genreList2));

        for (Book book : bookList) {
            bookRepository.insert(book);
        }

        List<Book> booksActual1 = new ArrayList<>();
        booksActual1.add(bookList.get(0));

        List<Book> booksActual2 = new ArrayList<>();
        booksActual2.add(bookList.get(0));
        booksActual2.add(bookList.get(1));

        List<Book> booksActual3 = new ArrayList<>();
        booksActual3.add(bookList.get(1));

        List<Book> booksExpected1 = bookRepository.getAllByGenre("Genre 1");
        List<Book> booksExpected2 = bookRepository.getAllByGenre("Genre 2");
        List<Book> booksExpected3 = bookRepository.getAllByGenre("Genre 3");

        assertEquals(booksExpected1, booksActual1);
        assertEquals(booksExpected2, booksActual2);
        assertEquals(booksExpected3, booksActual3);
    }

    /*
    @DisplayName("Получение списка авторов по ISBN книги")
    @Test
    void getAuthorsByIsbnTest() {
        List<Author> authorActualList = new ArrayList<>();
        authorActualList.add(new Author("Test record"));

        jdbc.update("INSERT INTO books (ISBN, Title) VALUES ('TEST_BOOK', 'Title')", Map.of());

        for (Author author : authorActualList) {
            jdbc.update("INSERT INTO book_authors (ISBN, AUTHOR_ID) VALUES ('TEST_BOOK', :id)", Map.of("id", author.getId()));
        }

        List<Author> authorExpected = bookRepository.getBooksAuthors("TEST_BOOK");
        assertFalse(authorExpected.isEmpty());
        assertEquals(authorExpected.size(), authorActualList.size());
        assertEquals(authorExpected, authorActualList);
    }*/

    /*@DisplayName("Получение списка авторов по ISBN книги")
    @Test
    void getGenresByIsbnTest() {
        List<Genre> genreActualList = new ArrayList<>();
        Genre genre1 = new Genre("Test record");
        genreActualList.add(genre1);

        for (Genre genre : genreActualList) {
            jdbc.update("INSERT INTO books (ISBN, Title) VALUES ('TEST_BOOK', 'Title')", Map.of());
            jdbc.update("INSERT INTO book_genres (ISBN, GENRE_ID) VALUES ('TEST_BOOK', :id)", Map.of("id", genre.getId()));
        }
        List<Genre> authorExpected = bookRepository.getBookGenres("TEST_BOOK");

        assertFalse(authorExpected.isEmpty());
        assertEquals(authorExpected.size(), genreActualList.size());
        assertTrue(authorExpected.equals(genreActualList));
    }*/
}
