package ru.otus.homework.hw06.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw06.entity.Author;
import ru.otus.homework.hw06.entity.Book;
import ru.otus.homework.hw06.entity.Comment;
import ru.otus.homework.hw06.entity.Genre;
import ru.otus.homework.hw06.exception.DataNotFountException;
import ru.otus.homework.hw06.exception.FieldRequiredException;
import ru.otus.homework.hw06.exception.ObjectExistsException;
import ru.otus.homework.hw06.repository.AuthorRepository;
import ru.otus.homework.hw06.repository.BookRepository;
import ru.otus.homework.hw06.repository.CommentRepository;
import ru.otus.homework.hw06.repository.GenreRepository;
import ru.otus.homework.hw06.service.BookService;
import ru.otus.homework.hw06.service.print.PrintService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    private final PrintService<Book> printService;

    private final OutputServiceStreams ioService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           CommentRepository commentRepository, PrintService<Book> printService,
                           OutputServiceStreams ioService) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.printService = printService;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.ioService = ioService;
    }

    @Override
    @Transactional
    public void updateTitle(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        ioService.outString(String.format("Updated %s book", bookRepository.update(book).getIsbn()));
    }

    @Override
    @Transactional
    public void add(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        bookRepository.insert(book);
        ioService.outString(String.format("Book added. ISBN: %s", book.getIsbn()));
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        Book book = bookRepository.getBookByIsbn(isbn);
        if (book == null) {
            throw new DataNotFountException(String.format("Book with ISBN %s not found", isbn));
        }
        bookRepository.deleteByIsbn(book.getIsbn());
        ioService.outString(String.format("Book deleted. ID: %s", book.getIsbn()));
    }

    @Override
    @Transactional(readOnly = true)
    public void getAll() {
        ioService.outString(printService.objectsToPrint(bookRepository.getAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public void getByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new FieldRequiredException("ISBN");
        }
        ioService.outString(printService.objectToPrint(bookRepository.getBookByIsbn(isbn)));
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllByAuthor(String fullName) {
        ioService.outString(printService.objectsToPrint(bookRepository.getAllByAuthor(fullName)));
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllByGenre(String genreName) {
        ioService.outString(printService.objectsToPrint(bookRepository.getAllByGenre(genreName)));
    }

    @Override
    @Transactional
    public void addGenreToBook(String isbn, String genreName) {
        Book book = bookRepository.getBookByIsbn(isbn);
        if (book != null) {
            Genre genre = genreRepository.getGenreByName(genreName);
            if (genre == null) {
                genre = genreRepository.save(new Genre(genreName));
            }

            if (book.getGenres().contains(genre)) {
                throw new ObjectExistsException("The book already has an added genre");
            } else {
                book.getGenres().add(genre);
                bookRepository.update(book);
            }
        }
    }

    @Override
    @Transactional
    public void addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.getBookByIsbn(isbn);
        if (book != null) {
            Author author = authorRepository.getByFullName(fullName);
            if (author == null) {
                author = authorRepository.save(new Author(fullName));
            }

            if (book.getAuthors().contains(author)) {
                throw new ObjectExistsException("The book already has an added author");
            } else {
                book.getAuthors().add(author);
            }
            bookRepository.update(book);
        }
    }

    @Override
    @Transactional
    public void addCommentToBook(String isbn, String commentText) {
        Book book = bookRepository.getBookByIsbn(isbn);
        if (book != null && commentText != null && !commentText.isEmpty()) {
            commentRepository.save(new Comment(commentText, book));
        }
    }
}
