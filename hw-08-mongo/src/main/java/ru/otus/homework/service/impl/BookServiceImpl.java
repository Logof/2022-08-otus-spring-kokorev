package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Comment;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.exception.FieldRequiredException;
import ru.otus.homework.exception.ObjectExistsException;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.CommentService;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.print.PrintService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final CommentService commentService;

    private final PrintService<Book> printService;

    private final OutputServiceStreams ioService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           GenreService genreService,
                           CommentService commentService, PrintService<Book> printService,
                           OutputServiceStreams ioService) {
        this.bookRepository = bookRepository;
        this.commentService = commentService;
        this.printService = printService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.ioService = ioService;
    }

    @Override
    @Transactional
    public void save(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }

        if (book.getAuthors().size() > 0) {
            attachBookToAuthor(book.getAuthors(), book.getIsbn());
        }

        if (book.getGenres().size() > 0) {
            attachBookToGenre(book.getGenres(), book.getIsbn());
        }

        bookRepository.save(book);
        ioService.outString(String.format("Book saved. ISBN: %s", bookRepository.save(book).getIsbn()));
    }


    private void attachBookToAuthor(List<String> authorList, String isbn) {
        for (String fullNAme : authorList) {
            Author author = authorService.findOrCreate(fullNAme);
            if (!author.getBookIsbns().contains(isbn)) {
                author.getBookIsbns().add(isbn);
                authorService.save(author);
            }
        }
    }

    private void attachBookToGenre(List<String> genreList, String isbn) {
        for (String genreName : genreList) {
            Genre genre = genreService.findOrCreate(genreName);
            if (!genre.getBookIsbns().contains(isbn)) {
                genre.getBookIsbns().add(isbn);
                genreService.save(genre);
            }
        }
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findById(isbn);
        if (book.isEmpty()) {
            throw new DataNotFountException(String.format("Book with ISBN %s not found", isbn));
        }
        bookRepository.delete(book.get());
        ioService.outString(String.format("Book deleted. ID: %s", book.get().getIsbn()));
    }

    @Override
    @Transactional(readOnly = true)
    public void getAll() {
        ioService.outString(printService.objectsToPrint(bookRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public void getByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new FieldRequiredException("ISBN");
        }
        ioService.outString(printService.objectToPrint(bookRepository.findById(isbn).orElse(null)));
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllByAuthor(String fullName) {
        ioService.outString(printService.objectsToPrint(bookRepository.findByAuthor(fullName)));
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllByGenre(String genreName) {
        ioService.outString(printService.objectsToPrint(bookRepository.getAllByGenre(genreName)));
    }

    @Override
    @Transactional
    public void addGenreToBook(String isbn, String genreName) {
        Optional<Book> bookOptional = bookRepository.findById(isbn);
        if (bookOptional.isPresent()) {

            attachBookToGenre(Collections.singletonList(genreName), isbn);

            if (bookOptional.get().getGenres().contains(genreName)) {
                throw new ObjectExistsException("The book already has an added genre");
            } else {
                bookOptional.get().getGenres().add(genreName);
                bookRepository.save(bookOptional.get());
            }
        }
    }

    @Override
    @Transactional
    public void addAuthorToBook(String isbn, String fullName) {
        Optional<Book> bookOptional = bookRepository.findById(isbn);
        if (bookOptional.isPresent()) {
            attachBookToGenre(Collections.singletonList(fullName), isbn);

            if (bookOptional.get().getGenres().contains(fullName)) {
                throw new ObjectExistsException("The book already has an added genre");
            } else {
                bookOptional.get().getGenres().add(fullName);
                bookRepository.save(bookOptional.get());
            }
        }
    }

    @Override
    @Transactional
    public void addCommentToBook(String isbn, String commentText) {
        Optional<Book> bookOptional = bookRepository.findById(isbn);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            Comment comment = commentService.createAndSave(commentText, book.getIsbn());
            book.getCommentIds().add(comment.getId());
            bookRepository.save(book);
        }
    }
}
