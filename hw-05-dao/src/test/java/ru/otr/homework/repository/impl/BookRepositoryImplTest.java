package ru.otr.homework.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.service.impl.OutputServiceStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тест BookDao")
@SpringBootTest(classes = Application.class)
public class BookRepositoryImplTest {

    @MockBean
    private OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    public void clearData() {
        jdbc.update("DELETE books", Map.of());
    }

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

        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Количество")
    @Test
    void countTest() {
        long beginRecordCount = bookRepository.count();
        Book book = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookRepository.insert(book);
        assertTrue(bookRepository.count() >= 1 && beginRecordCount < bookRepository.count());
    }

    @DisplayName("Получение книг по автору")
    @Test
    void getAllByAuthorTest(){
        List<Author> authorList1 = new ArrayList<>();
        authorList1.add(new Author(null, "Author 1"));
        authorList1.add(new Author(null, "Author 2"));

        List<Author> authorList2 = new ArrayList<>();
        authorList2.add(new Author(null, "Author 2"));
        authorList2.add(new Author(null, "Author 3"));

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
    void getAllByGenre(){
        List<Genre> genreList1 = new ArrayList<>();
        genreList1.add(new Genre(null, "Genre 1"));
        genreList1.add(new Genre(null, "Genre 2"));

        List<Genre> genreList2 = new ArrayList<>();
        genreList2.add(new Genre(null, "Genre 2"));
        genreList2.add(new Genre(null, "Genre 3"));

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
}
