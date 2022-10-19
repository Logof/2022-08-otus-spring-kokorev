package ru.otus.homework.repository.impl;

import lombok.extern.slf4j.Slf4j;
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

import static ru.otus.homework.mapper.BookMapper.bookWithAuthorsAndGenresResultExtractor;
import static ru.otus.homework.mapper.BookMapper.booksWithAuthorsAndGenresResultExtractor;

@Repository
@Slf4j
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
        Book book = jdbc.query("" +
                        "SELECT b.isbn, b.title," +
                        "       bg.id as `genres.id`, bg.genre_name as `genres.genreName`," +
                        "       ba.id as `authors.id`, ba.full_name as `authors.fullName`" +
                        "  FROM books b left join (SELECT a.id, a.full_name, ba.ISBN" +
                        "                            from book_authors ba " +
                        "                               , authors a " +
                        "                           WHERE ba.AUTHOR_ID = a.ID) ba on b.isbn = ba.isbn" +
                        "               left join (SELECT g.id, g.genre_name, bg.ISBN" +
                        "                            FROM book_genres  bg " +
                        "                               , genres g " +
                        "                           WHERE bg.GENRE_ID = g.ID) bg on b.isbn = bg.isbn" +
                        " WHERE b.isbn = :isbn",
                Map.of("isbn", isbn),
                bookWithAuthorsAndGenresResultExtractor
        );

        if (book == null) {
            throw new DataNotFountException("Not found or too many values found");
        }
        return book;
    }

    @Override
    public void delete(String isbn) {
        jdbc.update("DELETE books WHERE isbn = :isbn", Map.of("isbn", isbn));
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = jdbc.query("" +
                        "SELECT b.isbn, b.title," +
                        "       bg.id as `genres.id`, bg.genre_name as `genres.genreName`," +
                        "       ba.id as `authors.id`, ba.full_name as `authors.fullName`" +
                        "  FROM books b left join (SELECT a.id, a.full_name, ba.ISBN" +
                        "                            from book_authors ba " +
                        "                               , authors a " +
                        "                           WHERE ba.AUTHOR_ID = a.ID) ba on b.isbn = ba.isbn" +
                        "               left join (SELECT g.id, g.genre_name, bg.ISBN" +
                        "                            FROM book_genres  bg " +
                        "                               , genres g " +
                        "                           WHERE bg.GENRE_ID = g.ID) bg on b.isbn = bg.isbn",
                Map.of(),
                booksWithAuthorsAndGenresResultExtractor);
        if (books.size() == 0) {
            return books;
        }

        for (Book book : books) {
            book.setAuthors(authorRepository.getAuthorsByIsbn(book.getIsbn()));
            book.setGenres(genreRepository.getGenresByIsbn(book.getIsbn()));
        }
        return books;
    }


    private void addAuthorsFromBook(Book book) {
        if (book.getAuthors() != null) {
            for (Author author : book.getAuthors()) {
                int position = book.getAuthors().indexOf(author);

                if (Objects.equals(author.getId(), null)) {
                    Author foundAuthor = authorRepository.getByFullName(author.getFullName());

                    if (Objects.isNull(foundAuthor)) {
                        author.setId(authorRepository.insert(author).getId());
                    } else {
                        author.setId(foundAuthor.getId());
                    }
                }

                addAuthorToBook(book.getIsbn(), author.getId());

                book.getAuthors().set(position, author);
            }
        }
    }

    private void addGenresFromBook(Book book) {
        if (book.getGenres() != null) {
            for (Genre genre : book.getGenres()) {
                int position = book.getGenres().indexOf(genre);

                if (Objects.equals(genre.getId(), null)) {
                    Genre findenGenre = genreRepository.getGenreByName(genre.getGenreName());

                    if (Objects.isNull(findenGenre)) {
                        genre.setId(genreRepository.insert(genre).getId());
                    } else {
                        genre.setId(findenGenre.getId());
                    }
                }
                addGenreToBook(book.getIsbn(), genre.getId());

                book.getGenres().set(position, genre);
            }
        }
    }

    @Override
    public int insert(Book book) {
        int result = jdbc.update("INSERT INTO books (isbn, title) VALUES (:isbn, :title)",
                Map.of("isbn", book.getIsbn(), "title", book.getTitle()));
        //TODO подумать над именем
        addAuthorsFromBook(book);
        addGenresFromBook(book);

        return result;
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
        return jdbc.query("" +
                        "SELECT b.isbn, b.title," +
                        "       bg.id as `genres.id`, bg.genre_name as `genres.genreName`," +
                        "       ba.id as `authors.id`, ba.full_name as `authors.fullName`" +
                        "  FROM books b left join (SELECT a.id, a.full_name, ba.ISBN" +
                        "                            from book_authors ba " +
                        "                               , authors a " +
                        "                           WHERE ba.AUTHOR_ID = a.ID) ba on b.isbn = ba.isbn" +
                        "               left join (SELECT g.id, g.genre_name, bg.ISBN" +
                        "                            FROM book_genres  bg " +
                        "                               , genres g " +
                        "                           WHERE bg.GENRE_ID = g.ID) bg on b.isbn = bg.isbn" +
                        " WHERE b.isbn IN (SELECT ISBN FROM BOOK_AUTHORS ba2, AUTHORS a2  " +
                        "                   WHERE a2.FULL_NAME like :fullName AND a2.ID = ba2.AUTHOR_ID)",
                Map.of("fullName", "%" + fullName + "%"),
                booksWithAuthorsAndGenresResultExtractor);
    }

    @Override
    public List<Book> getAllByGenre(String genreName) {
        return jdbc.query("" +
                        "SELECT b.isbn, b.title," +
                        "       bg.id as `genres.id`, bg.genre_name as `genres.genreName`," +
                        "       ba.id as `authors.id`, ba.full_name as `authors.fullName`" +
                        "  FROM books b left join (SELECT a.id, a.full_name, ba.ISBN" +
                        "                            from book_authors ba " +
                        "                               , authors a " +
                        "                           WHERE ba.AUTHOR_ID = a.ID) ba on b.isbn = ba.isbn" +
                        "               left join (SELECT g.id, g.genre_name, bg.ISBN" +
                        "                            FROM book_genres  bg " +
                        "                               , genres g " +
                        "                           WHERE bg.GENRE_ID = g.ID) bg on b.isbn = bg.isbn" +
                        " WHERE b.isbn IN (SELECT ISBN FROM BOOK_GENRES ba2, GENRES g2  " +
                        "                   WHERE g2.GENRE_NAME like :genreName AND g2.ID = ba2.GENRE_ID)",
                Map.of("genreName", "%" + genreName + "%"),
                booksWithAuthorsAndGenresResultExtractor);
    }

    @Override
    public void addAuthorToBook(String isbn, long authorId) {
        jdbc.update("INSERT INTO BOOK_AUTHORS (isbn, author_id) VALUES (:isbn, :authorId)",
                Map.of("isbn", isbn, "authorId", authorId));


        log.info("insert links: {}", jdbc.queryForObject("Select count(1) from BOOK_AUTHORS where isbn = :isbn and author_id = :authorId",
                Map.of("isbn", isbn, "authorId", authorId), Long.class));
    }

    @Override
    public void addGenreToBook(String isbn, long genreId) {
        jdbc.update("INSERT INTO BOOK_GENRES (isbn, GENRE_ID) VALUES (:isbn, :genreId)",
                Map.of("isbn", isbn, "genreId", genreId));
    }


}
