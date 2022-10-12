package ru.otus.homework.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookAssociationDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.entity.Book;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.mapper.BookMapper;

import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final BookAssociationDao bookAssociationDao;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       BookAssociationDao bookAssociationDao) {
        this.jdbc = namedParameterJdbcOperations;
        this.bookAssociationDao = bookAssociationDao;
    }

    @Override
    public long count() {
        Long count = jdbc.queryForObject("SELECT count(1) FROM books",
                Map.of(), Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public Book getBookById(String isbn) {

        List<Book> books = jdbc.query("SELECT isbn, title FROM books WHERE isbn = :isbn",
                Map.of("isbn", isbn), new BookMapper());

        if (books.size() != 1) {
            throw new DataNotFountException("Not found or too many values found");
        }

        books.get(0).setAuthors(bookAssociationDao.getAutors(books.get(0).getIsbn()));
        books.get(0).setGenres(bookAssociationDao.getGenres(books.get(0).getIsbn()));

        return books.get(0);
    }

    @Override
    public void delete(String isbn) {
        jdbc.update("DELETE books WHERE isbn = :isbn", Map.of("isbn", isbn));
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = jdbc.query("SELECT isbn, title FROM books", Map.of(), new BookMapper());
        if (books.size() == 0) {
            return books;
        }

        for (Book book : books) {
            book.setAuthors(bookAssociationDao.getAutors(book.getIsbn()));
            book.setGenres(bookAssociationDao.getGenres(book.getIsbn()));
        }
        return books;
    }

    @Override
    public int insert(Book book) {
        return jdbc.update("INSERT INTO books (isbn, title) VALUES (:isbn, :title)",
                Map.of("isbn", book.getIsbn(), "title", book.getTitle()));
    }

    @Override
    public int update(Book book) {
        if (book == null
                || book.getIsbn() == null
                || book.getIsbn().isBlank()
                || book.getTitle() == null
                || book.getTitle().isBlank()) {
            return 0;
        }

        return jdbc.update("UPDATE books set title = :new_title WHERE isbn = :isbn",
                Map.of("isbn", book.getIsbn(), "new_title", book.getTitle()));
    }

}
