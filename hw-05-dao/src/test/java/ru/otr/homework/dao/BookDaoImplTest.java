package ru.otr.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.dao.BookDao;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс BookDaoImpl")
@SpringBootTest

public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void contextLoads() throws Exception {
        assertThat(bookDao).isNotNull();
    }

    /*


    @DisplayName("Добавление")
    @Test
    void insertTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        Integer countInsertRow = bookDao.insert(bookActual);
        assertEquals(countInsertRow, 1);
    }

    @DisplayName("Обновление")
    @Test
    void updateTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookDao.insert(bookActual);
        bookActual.setTitle("Test Title");
        Integer countUpdateRow = bookDao.update(bookActual);
        assertEquals(countUpdateRow, 1);

        Book bookExpected = bookDao.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        Book bookExpected = new Book("YYY-Y-YYY-YYYYY-Y", "Test title");
        bookDao.insert(bookActual);
        bookDao.insert(bookExpected);

        bookDao.delete(bookActual.getIsbn());

        boolean bookActualNotDelete = true;
        boolean bookExpectedNotDelete = true;

        for (Book book : bookDao.getAll()) {
            if (book.getTitle().equals(bookActual.getTitle())) {
                bookActualNotDelete = false;
            }
            if (book.getTitle().equals(bookExpected.getTitle())) {
                bookExpectedNotDelete = false;
            }
        }
        assertFalse(bookActualNotDelete);
        assertTrue(bookExpectedNotDelete);
    }

    @DisplayName("Получение всех")
    @Test
    void getAll() {
        List<Book> booksBeginCount = bookDao.getAll();
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookDao.insert(bookActual);
        List<Book> booksFinalCount = bookDao.getAll();
        assertTrue(booksFinalCount.size() - booksBeginCount.size() >= 1);
    }


    @DisplayName("Получение одного")
    @Test
    void getById() {
        Book bookActual = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookDao.insert(bookActual);

        Book bookExpected = bookDao.getBookById(bookActual.getIsbn());
        assertEquals(bookExpected, bookActual);

        assertEquals(bookExpected, bookActual);
    }

    @DisplayName("Количество")
    @Test
    void count() {
        long beginRecordCount = bookDao.count();
        Book book = new Book("XXX-X-XXX-XXXXX-X", "Test title");
        bookDao.insert(book);
        assertTrue(bookDao.count() >= 1 && beginRecordCount < bookDao.count());
    }*/
}
