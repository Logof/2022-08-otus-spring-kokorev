package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Comment;
import ru.otus.homework.hw08.entity.Genre;
import ru.otus.homework.hw08.exception.DataNotFountException;
import ru.otus.homework.hw08.exception.FieldRequiredException;
import ru.otus.homework.hw08.exception.ObjectExistsException;
import ru.otus.homework.hw08.repository.AuthorRepository;
import ru.otus.homework.hw08.repository.BookRepository;
import ru.otus.homework.hw08.repository.GenreRepository;
import ru.otus.homework.hw08.service.BookService;
import ru.otus.homework.hw08.service.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService, CommentService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Book updateTitle(String isbn, String newTitle) {
        if (isbn == null || isbn.isBlank() || newTitle == null || newTitle.isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        book.setTitle(newTitle);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book add(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new FieldRequiredException("ISBN");
        }
        return bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllByAuthor(String fullName) {
        return bookRepository.findAllByAuthorsLike("%" + fullName + "%");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllByGenre(String genreName) {
        return bookRepository.findAllByGenresLike("%" + genreName + "%");
    }

    @Override
    @Transactional
    public Book addGenreToBook(String isbn, String genreName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Genre genre = genreRepository.findByGenreNameLike(genreName);

        if (genre != null && book.getGenres().contains(genre)
                || genre != null && genre.getIsbnList().contains(isbn)) {
            throw new ObjectExistsException("The book already has an added genre");
        }

        if (Objects.isNull(genre)) {
            genre = new Genre(genreName, new ArrayList<>());
        }
        genre.getIsbnList().add(isbn);
        book.getGenres().add(genreName);

        genreRepository.save(genre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Author author = authorRepository.findByFullNameLike(fullName);

        if (author != null && book.getAuthors().contains(author)
                || author != null && author.getIsbnList().contains(isbn)) {
            throw new ObjectExistsException("The book already has an added author");
        }

        if (Objects.isNull(author)) {
            author = new Author(fullName, new ArrayList<>());
        }
        author.getIsbnList().add(isbn);
        book.getAuthors().add(fullName);
        authorRepository.save(author);

        return bookRepository.save(book);

    }

    @Override
    @Transactional
    public Book addCommentToBook(String isbn, String commentText) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        if (commentText == null || commentText.isEmpty()) {
            throw new FieldRequiredException("commentText");
        }

        book.getComments().add(new Comment(commentText));
        return bookRepository.save(book);
    }

    @Override
    public List<Comment> getCommentsByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        return book.getComments();
    }

    @Override
    public Book deleteCommentByIndex(String isbn, int index) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        book.getComments().remove(index);
        return bookRepository.save(book);
    }
}
