package ru.otus.homework.repository.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.AuthorDao;
import ru.otus.homework.repository.BookDao;
import ru.otus.homework.repository.GenreDao;

import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       AuthorDao authorDao,
                       GenreDao genreDao) {
        this.jdbc = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public long count() {
        Long count = jdbc.queryForObject("SELECT count(1) FROM books",
                Map.of(), Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public Book getBookById(final String isbn) {
        Book book = jdbc.queryForObject("SELECT isbn, title FROM books WHERE isbn = :isbn",
                Map.of("isbn", isbn), Book.class);

        if (book == null) {
            throw new DataNotFountException("Not found or too many values found");
        }

        book.setAuthors(authorDao.getAuthorsByIsbn(isbn));
        book.setGenres(genreDao.getGenresByIsbn(isbn));

        return book;
    }

    @Override
    public void delete(String isbn) {
        jdbc.update("DELETE books WHERE isbn = :isbn", Map.of("isbn", isbn));
        jdbc.update("DELETE assoc WHERE isbn = :isbn", Map.of("isbn", isbn));
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = jdbc.queryForList("SELECT isbn, title FROM books", Map.of(), Book.class);
        if (books.size() == 0) {
            return books;
        }

        for (Book book : books) {
            book.setAuthors(authorDao.getAuthorsByIsbn(book.getIsbn()));
            book.setGenres(genreDao.getGenresByIsbn(book.getIsbn()));
        }
        return books;
    }

    private void insertAssoc(String isbn, long externalId, String externalClass) {
        jdbc.update("INSERT INTO assoc (isbn, external_id, external_class) VALUES (:isbn, :externalId, externalClass)",
                Map.of("isbn", isbn,
                        "externalId", externalId,
                        "externalClass", externalClass));
    }

    @Override
    public int insert(Book book) {
        jdbc.update("INSERT INTO books (isbn, title) VALUES (:isbn, :title)",
                Map.of("isbn", book.getIsbn(), "title", book.getTitle()));

        if (book.getAuthors() != null) {
            for (Author author : book.getAuthors()) {
                authorDao.insert(author);
                insertAssoc(book.getIsbn(), author.getId(), Author.class.getSimpleName());
            }
        }

        if (book.getGenres() != null) {
            for (Genre genre : book.getGenres()) {
                genreDao.insert(genre);
                insertAssoc(book.getIsbn(), genre.getId(), Genre.class.getSimpleName());
            }
        }
        //TODO а надо ли?
        return 1;
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
