package ru.otr.homework.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.entity.Book;
import ru.otus.homework.repository.BookDao;
import ru.otus.homework.service.impl.OutputServiceStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тест BookDao")
@SpringBootTest(classes = Application.class)
public class BookDaoImplTest {

    @MockBean
    OutputServiceStreams outputServiceStreams;

    @Autowired
    private BookDao bookDao;

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
        int countInsertRow = bookDao.insert(bookActual);
        assertEquals(countInsertRow, 1);

        Book bookExpected = bookDao.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookDao.insert(bookActual);
        bookActual.setTitle("Test Title");
        int countUpdateRow = bookDao.update(bookActual);
        assertEquals(countUpdateRow, 1);

        Book bookExpected = bookDao.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test1");
        Book bookExpected = new Book("YYY-Y-YYY-YYYYY-Y", "Test2");
        bookDao.insert(bookActual);
        bookDao.insert(bookExpected);

        bookDao.delete(bookActual.getIsbn());

        boolean bookActualRecordFound = false;
        boolean bookExpectedRecordFound = false;

        for (Book book : bookDao.getAll()) {
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

        bookDao.insert(bookActual);
        List<Book> booksExpectedList = bookDao.getAll();
        assertEquals(booksExpectedList, booksActualList);
    }


    @DisplayName("Получение одной записи")
    @Test
    void getByIdTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookDao.insert(bookActual);

        Book bookExpected = bookDao.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);

        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Количество")
    @Test
    void countTest() {
        long beginRecordCount = bookDao.count();
        Book book = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookDao.insert(book);
        assertTrue(bookDao.count() >= 1 && beginRecordCount < bookDao.count());
    }
}
