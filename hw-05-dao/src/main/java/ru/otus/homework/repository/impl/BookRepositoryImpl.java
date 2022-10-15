package ru.otus.homework.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.GenreRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final NamedParameterJdbcOperations jdbc;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                              AuthorRepository authorRepository,
                              GenreRepository genreRepository) {
        this.jdbc = namedParameterJdbcOperations;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public long count() {
        return jdbc.queryForObject("SELECT count(1) FROM books", Map.of(), Long.class);
    }

    @Override
    public Book getBookById(final String isbn) {
        Book book = jdbc.queryForObject("SELECT isbn, title FROM books WHERE isbn = :isbn",
                Map.of("isbn", isbn), new BeanPropertyRowMapper<>(Book.class));

        if (book == null) {
            throw new DataNotFountException("Not found or too many values found");
        }

        book.setAuthors(authorRepository.getAuthorsByIsbn(isbn));
        book.setGenres(genreRepository.getGenresByIsbn(isbn));

        return book;
    }

    @Override
    public void delete(String isbn) {
        jdbc.update("DELETE books WHERE isbn = :isbn", Map.of("isbn", isbn));
        jdbc.update("DELETE assoc WHERE isbn = :isbn", Map.of("isbn", isbn));
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = jdbc.query("SELECT isbn, title FROM books", Map.of(),
                new BeanPropertyRowMapper<>(Book.class));
        if (books.size() == 0) {
            return books;
        }

        for (Book book : books) {
            book.setAuthors(authorRepository.getAuthorsByIsbn(book.getIsbn()));
            book.setGenres(genreRepository.getGenresByIsbn(book.getIsbn()));
        }
        return books;
    }

    private void insertAssoc(String isbn, long externalId, String externalClass) {
        jdbc.update("INSERT INTO assoc (isbn, external_id, external_class) VALUES (:isbn, :externalId, :externalClass)",
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
                if (Objects.equals(author.getId(), null)) {
                    authorRepository.insert(author);
                }
                insertAssoc(book.getIsbn(), author.getId(), Author.class.getSimpleName());
            }
        }

        if (book.getGenres() != null) {
            for (Genre genre : book.getGenres()) {
                if (Objects.equals(genre.getId(), null)) {
                    genreRepository.insert(genre);
                }
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

    @Override
    public List<Book> getAllByAuthor(String fullName) {
        List<Book> books = jdbc.query(
                "SELECT b.isbn, b.title " +
                    "  FROM books b, assoc a, authors au " +
                    " WHERE b.isbn = a.isbn AND a.external_id = au.id AND a.external_class = :externalClass" +
                    "   AND au.full_name like :fullName",
                Map.of("externalClass", Author.class.getSimpleName(),
                        "fullName", "%"+fullName+"%"),
                new BeanPropertyRowMapper<>(Book.class));
        if (books.size() == 0) {
            return books;
        }

        for (Book book : books) {
            book.setAuthors(authorRepository.getAuthorsByIsbn(book.getIsbn()));
            book.setGenres(genreRepository.getGenresByIsbn(book.getIsbn()));
        }
        return books;
    }

    @Override
    public List<Book> getAllByGenre(String genreName) {
        List<Book> books = jdbc.query("SELECT b.isbn, b.title FROM books b, assoc a, genres g " +
                        "WHERE b.isbn = a.isbn AND a.external_id = g.id AND a.external_class = :externalClass" +
                        "  AND g.genre_name like :genreName", Map.of("externalClass", Genre.class.getSimpleName(),
                        "genreName", "%"+genreName+"%"),
                new BeanPropertyRowMapper<>(Book.class));
        if (books.size() == 0) {
            return books;
        }

        for (Book book : books) {
            book.setAuthors(authorRepository.getAuthorsByIsbn(book.getIsbn()));
            book.setGenres(genreRepository.getGenresByIsbn(book.getIsbn()));
        }
        return books;
    }

}
