package ru.otr.homework.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.entity.Book;
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
}
