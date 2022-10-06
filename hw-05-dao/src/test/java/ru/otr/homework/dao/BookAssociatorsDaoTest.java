package ru.otr.homework.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.homework.Application;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.BookAssociation;
import ru.otus.homework.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест BookAssociatorsDao")
@SpringBootTest(classes = Application.class)
public class BookAssociatorsDaoTest {

    @Autowired
    private BookAssociationDao bookAssociationDao;

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @BeforeEach
    private void clearData() {
        jdbc.update("DELETE assoc", Map.of());
    }

    @DisplayName("Удаление")
    @Test
    void deleteTest() {
        String isbn = "XXX-X-XXX-XXXXX-X";
        BookAssociation bookAssocAuthorRow1 = new BookAssociation(isbn, 1, Author.class.getSimpleName());
        BookAssociation bookAssocAuthorRow2 = new BookAssociation(isbn, 2, Author.class.getSimpleName());
        BookAssociation bookAssocGenreRow1 = new BookAssociation(isbn, 1, Genre.class.getSimpleName());
        BookAssociation bookAssocGenreRow2 = new BookAssociation(isbn, 2, Genre.class.getSimpleName());
        bookAssociationDao.insert(bookAssocAuthorRow1);
        bookAssociationDao.insert(bookAssocAuthorRow2);
        bookAssociationDao.insert(bookAssocGenreRow1);
        bookAssociationDao.insert(bookAssocGenreRow2);

        bookAssociationDao.delete(bookAssocAuthorRow1);

        bookAssociationDao.delete(bookAssocGenreRow2);

        boolean bookAssocAuthorRow1Found = false;
        boolean bookAssocAuthorRow2Found = false;
        boolean bookAssocGenreRow1Found = false;
        boolean bookAssocGenreRow2Found = false;

        List<Long> listAuthors = bookAssociationDao.getExternalIdsById(isbn, Author.class.getSimpleName());
        List<Long> listGenres = bookAssociationDao.getExternalIdsById(isbn, Genre.class.getSimpleName());

        for (Long id : listAuthors) {
            if (Objects.equals(id, bookAssocAuthorRow1.getExternalId())) {
                bookAssocAuthorRow1Found = true;
            }
            if (Objects.equals(id, bookAssocAuthorRow2.getExternalId())) {
                bookAssocAuthorRow2Found = true;
            }
        }
        for (Long id : listGenres) {
            if (Objects.equals(id, bookAssocGenreRow1.getExternalId())) {
                bookAssocGenreRow1Found = true;
            }
            if (Objects.equals(id, bookAssocGenreRow2.getExternalId())) {
                bookAssocGenreRow1Found = true;
            }
        }
        assertFalse(bookAssocAuthorRow1Found);
        assertTrue(bookAssocAuthorRow2Found);

        assertTrue(bookAssocGenreRow1Found);
        assertFalse(bookAssocGenreRow2Found);
    }

    @DisplayName("Добавление")
    @Test
    void insertTest() {
        BookAssociation bookAssociationActual = new BookAssociation("XXX-X-XXX-XXXXX-X", 1, "Test record");
        int countInsertRow = bookAssociationDao.insert(bookAssociationActual);
        assertEquals(countInsertRow, 1);

        List<Long> ids = bookAssociationDao.getExternalIdsById("XXX-X-XXX-XXXXX-X", "Test record");
        assertTrue(ids.get(0) == 1 && ids.size() == 1);
    }

    @DisplayName("Добавление")
    @Test
    void isExistTest() {
        BookAssociation bookAssociation = new BookAssociation("XXX-X-XXX-XXXXX-X", 1, "Test record");
        int countInsertRow = bookAssociationDao.insert(bookAssociation);
        assertFalse(bookAssociationDao.isExist(bookAssociation));
        assertTrue(bookAssociationDao.isExist(new BookAssociation("XXX-X-XXX-XXXXX-X", 2, "Test record")));
    }

    @DisplayName("Получение списка авторов")
    @Test
    void getAuthorListTest() {
        String isbn = "XXX-X-XXX-XXXXX-X";
        BookAssociation bookAssocGenreRow1 = new BookAssociation(isbn, 1, Author.class.getSimpleName());
        BookAssociation bookAssocGenreRow2 = new BookAssociation(isbn, 2, Author.class.getSimpleName());
        bookAssociationDao.insert(bookAssocGenreRow1);
        bookAssociationDao.insert(bookAssocGenreRow2);


        List<Long> authorListExpected = bookAssociationDao.getExternalIdsById(isbn, Author.class.getSimpleName());
        List<Long> authorListActual = new ArrayList<>();
        authorListActual.add(1L);
        authorListActual.add(2L);

        assertEquals(authorListExpected, authorListActual);
    }

    @DisplayName("Получение списка жанров")
    @Test
    void getGenreListTest() {
        String isbn = "XXX-X-XXX-XXXXX-X";
        BookAssociation bookAssocGenreRow1 = new BookAssociation(isbn, 1, Genre.class.getSimpleName());
        BookAssociation bookAssocGenreRow2 = new BookAssociation(isbn, 2, Genre.class.getSimpleName());
        bookAssociationDao.insert(bookAssocGenreRow1);
        bookAssociationDao.insert(bookAssocGenreRow2);


        List<Long> genreListExpected = bookAssociationDao.getExternalIdsById(isbn, Genre.class.getSimpleName());
        List<Long> genreListActual = new ArrayList<>();
        genreListActual.add(1L);
        genreListActual.add(2L);

        assertEquals(genreListExpected, genreListActual);
    }

    @DisplayName("Получение списка Id связанных сущьностей")
    @Test
    void getExternalIdsByIdTest() {
        String isbn1 = "XXX-X-XXX-XXXXX-X";
        String isbn2 = "XXX-X-XXX-XXXXX-Y";
        BookAssociation bookAssocGenreRow1 = new BookAssociation(isbn1, 1, Genre.class.getSimpleName());
        BookAssociation bookAssocGenreRow2 = new BookAssociation(isbn1, 1, Author.class.getSimpleName());
        bookAssociationDao.insert(bookAssocGenreRow1);
        bookAssociationDao.insert(bookAssocGenreRow2);


        assertTrue(bookAssociationDao.getExternalIdsById(isbn1, Genre.class.getSimpleName()).size() == 1);
        assertTrue(bookAssociationDao.getExternalIdsById(isbn1, Author.class.getSimpleName()).size() == 1);
        assertFalse(bookAssociationDao.getExternalIdsById(isbn2, Genre.class.getSimpleName()).size() == 1);
        assertFalse(bookAssociationDao.getExternalIdsById(isbn2, Author.class.getSimpleName()).size() == 1);

    }

    @DisplayName("Проверка существования связи")
    @Test
    void checkExistExternalLinkTest() {
        String isbn = "XXX-X-XXX-XXXXX-X";
        BookAssociation bookAssocGenreRow1 = new BookAssociation(isbn, 1, Genre.class.getSimpleName());
        BookAssociation bookAssocGenreRow1_1 = new BookAssociation(null, 1, Genre.class.getSimpleName());
        BookAssociation bookAssocGenreRow2 = new BookAssociation(isbn, 1, Author.class.getSimpleName());
        BookAssociation bookAssocGenreRow2_1 = new BookAssociation(null, 1, Author.class.getSimpleName());
        BookAssociation bookAssocGenreRow3 = new BookAssociation(isbn, 2, Genre.class.getSimpleName());
        BookAssociation bookAssocGenreRow3_1 = new BookAssociation(null, 2, Genre.class.getSimpleName());
        BookAssociation bookAssocGenreRow4 = new BookAssociation(isbn, 2, Author.class.getSimpleName());
        BookAssociation bookAssocGenreRow4_1 = new BookAssociation(null, 2, Author.class.getSimpleName());
        bookAssociationDao.insert(bookAssocGenreRow1);
        bookAssociationDao.insert(bookAssocGenreRow2);

        assertTrue(bookAssociationDao.isExist(bookAssocGenreRow1));
        assertTrue(bookAssociationDao.isExist(bookAssocGenreRow2));
        assertTrue(bookAssociationDao.isExist(bookAssocGenreRow1_1));
        assertTrue(bookAssociationDao.isExist(bookAssocGenreRow2_1));
        assertFalse(bookAssociationDao.isExist(bookAssocGenreRow3));
        assertFalse(bookAssociationDao.isExist(bookAssocGenreRow4));
        assertFalse(bookAssociationDao.isExist(bookAssocGenreRow3_1));
        assertFalse(bookAssociationDao.isExist(bookAssocGenreRow4_1));
    }


}
