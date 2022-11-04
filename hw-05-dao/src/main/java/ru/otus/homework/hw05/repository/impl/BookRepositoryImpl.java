package ru.otus.homework.hw05.repository.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.hw05.entity.Author;
import ru.otus.homework.hw05.entity.Book;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.helper.BookExtractor;
import ru.otus.homework.hw05.repository.AuthorRepository;
import ru.otus.homework.hw05.repository.BookRepository;
import ru.otus.homework.hw05.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public Book getBookById(final String isbn) {
        Map<String, Book> bookMap = jdbc.query("" +
                        "SELECT b.isbn, b.title," +
                        "       g.id as `genre.id`, g.genre_name as `genre.genreName`" +
                        "  FROM books b left join book_genres  bg on b.isbn = bg.isbn" +
                        "               left join genres       g  on g.ID   = bg.GENRE_ID" +
                        " WHERE b.isbn = :isbn",
                Map.of("isbn", isbn),
                new BookExtractor()
        );
        List<Author> authorList = getBooksAuthorsByIsbn(isbn);
        List<BookAuthorRelation> authorRelations = getBookAuthorRelationsByIsbns(new ArrayList<>(Objects.requireNonNull(bookMap).keySet()));

        mergeAuthorsInfo(bookMap, authorList, authorRelations);
        return bookMap.get(isbn);
    }

    private List<BookAuthorRelation> getAllBookAuthorRelations() {
        return jdbc.query("select isbn, author_id from book_authors ba order by isbn, author_id",
                (rs, i) -> new BookAuthorRelation(rs.getString("isbn"), rs.getLong("author_id")));
    }

    private List<BookAuthorRelation> getBookAuthorRelationsByIsbns(List<String> isbns) {
        return jdbc.query("select distinct isbn, author_id from book_authors ba where isbn in (:isbns) order by isbn, author_id",
                Map.of("isbns", isbns),
                (rs, i) -> new BookAuthorRelation(rs.getString("isbn"), rs.getLong("author_id")));
    }

    private void mergeAuthorsInfo(Map<String, Book> books, List<Author> authors,
                                  List<BookAuthorRelation> relations) {
        Map<Long, Author> authorsMap = authors.stream().collect(Collectors.toMap(Author::getId, Function.identity()));
        relations.forEach(r -> {
            if (books.containsKey(r.getIsbn()) && authorsMap.containsKey(r.getAuthorId())) {
                books.get(r.getIsbn()).getAuthors().add(authorsMap.get(r.getAuthorId()));
            }
        });
    }


    @Override
    public void delete(String isbn) {
        jdbc.update("DELETE books WHERE isbn = :isbn", Map.of("isbn", isbn));
    }

    @Override
    public List<Book> getAll() {
        List<Author> authorList = authorRepository.getAll();
        List<BookAuthorRelation> relations = getAllBookAuthorRelations();
        Map<String, Book> bookMap = jdbc.query("" +
                        "SELECT distinct b.isbn, b.title," +
                        "       g.id as `genre.id`, g.genre_name as `genre.genreName`" +
                        "  FROM books b left join book_genres  bg on b.isbn = bg.isbn" +
                        "               left join genres       g  on g.ID   = bg.GENRE_ID",
                Map.of(),
                new BookExtractor());
        mergeAuthorsInfo(bookMap, authorList, relations);
        return new ArrayList<>(Objects.requireNonNull(bookMap).values());
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
                    Genre foundGenre = genreRepository.getGenreByName(genre.getGenreName());

                    if (Objects.isNull(foundGenre)) {
                        genre.setId(genreRepository.insert(genre).getId());
                    } else {
                        genre.setId(foundGenre.getId());
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

    /*
     * Т.к. даже у писательницы Дарьи Донцовой под ее именем вышло всего 100 книг, то не вижу смысла оптимизировать запрос
     * для сокращения кол-ва строк в результате запроса.
     */
    @Override
    public List<Book> getAllByAuthor(String fullName) {
        Map<String, Book> bookMap = jdbc.query("" +
                        "SELECT distinct b.isbn, b.title," +
                        "       g.id as `genre.id`, g.genre_name as `genre.genreName`," +
                        "  FROM books b  left join book_genres  bg on b.isbn = bg.isbn" +
                        "                left join genres       g  on g.ID   = bg.GENRE_ID" +
                        " WHERE b.isbn IN (SELECT ISBN FROM BOOK_AUTHORS ba2 inner join AUTHORS a2  " +
                        "                   WHERE a2.FULL_NAME like :fullName AND a2.ID = ba2.AUTHOR_ID)",
                Map.of("fullName", "%" + fullName + "%"),
                new BookExtractor());
        List<Author> authorList = getBooksAuthorsByFullName(fullName);
        List<BookAuthorRelation> authorRelations = getBookAuthorRelationsByIsbns(new ArrayList<>(Objects.requireNonNull(bookMap).keySet()));

        mergeAuthorsInfo(bookMap, authorList, authorRelations);
        return new ArrayList<>(Objects.requireNonNull(bookMap).values());
    }

    @Override
    public List<Book> getAllByGenre(String genreName) {

        Map<String, Book> bookMap = jdbc.query("" +
                        "SELECT distinct b.isbn, b.title," +
                        "       g.id as `genre.id`, g.genre_name as `genre.genreName`" +
                        "  FROM books b inner join book_genres  bg on b.isbn = bg.isbn" +
                        "               inner join genres       g  on g.ID   = bg.GENRE_ID" +
                        " WHERE b.isbn IN (SELECT ISBN FROM BOOK_GENRES bg2 inner join GENRES g2  " +
                        "                   WHERE g2.GENRE_NAME like :genreName AND g2.ID = bg2.GENRE_ID)",
                Map.of("genreName", "%" + genreName + "%"),
                new BookExtractor());

        List<Author> authorList = getBooksAuthorByGenreName(genreName);
        List<BookAuthorRelation> relations = getBookAuthorRelationsByIsbns(new ArrayList<>(Objects.requireNonNull(bookMap).keySet()));
        mergeAuthorsInfo(bookMap, authorList, relations);
        return new ArrayList<>(Objects.requireNonNull(bookMap).values());
    }


    //TODO имя не красивое
    private List<Author> getBooksAuthorsByFullName(String fullName) {
        return jdbc.query("" +
                        "SELECT distinct a.id, a.full_name " +
                        "  FROM authors a inner join book_authors s on s.author_id = a.id " +
                        " WHERE exists (select 1 from authors aa inner join book_authors ba on ba.author_id = aa.id " +
                        "                where aa.full_name like :fullName" +
                        "                  and ba.isbn = s.isbn)",
                Map.of("fullName", "%" + fullName + "%"),
                new BeanPropertyRowMapper<>(Author.class));
    }

    private List<Author> getBooksAuthorByGenreName(String genreName) {
        return jdbc.query("" +
                        "SELECT distinct a.id, a.full_name " +
                        "  FROM authors a inner join book_authors ba on ba.author_id = a.id " +
                        "                 inner join book_genres  bg on ba.isbn = bg.isbn" +
                        "                 inner join genres       g  on bg.genre_id = g.id" +
                        " WHERE g.genre_name like :genreName",
                Map.of("genreName", "%" + genreName + "%"),
                new BeanPropertyRowMapper<>(Author.class));
    }

    private List<Author> getBooksAuthorsByIsbn(String isbn) {
        return jdbc.query("" +
                        "SELECT distinct a.id, a.full_name " +
                        "  FROM authors a inner join book_authors s on s.author_id = a.id " +
                        " WHERE s.isbn = :isbn",
                Map.of("isbn", isbn),
                new BeanPropertyRowMapper<>(Author.class));
    }

    @Override
    public void addAuthorToBook(String isbn, long authorId) {
        jdbc.update("INSERT INTO BOOK_AUTHORS (isbn, author_id) VALUES (:isbn, :authorId)",
                Map.of("isbn", isbn, "authorId", authorId));
    }

    @Override
    public void addGenreToBook(String isbn, long genreId) {
        jdbc.update("INSERT INTO BOOK_GENRES (isbn, GENRE_ID) VALUES (:isbn, :genreId)",
                Map.of("isbn", isbn, "genreId", genreId));
    }


    @RequiredArgsConstructor
    @Getter
    private static class BookAuthorRelation {
        private final String isbn;
        private final long authorId;
    }
}
