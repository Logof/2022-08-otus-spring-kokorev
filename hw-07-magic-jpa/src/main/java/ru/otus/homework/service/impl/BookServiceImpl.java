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
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.GenreRepository;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.print.PrintService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final PrintService<Book> printService;

    private final OutputServiceStreams ioService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           PrintService<Book> printService,
                           OutputServiceStreams ioService) {
        this.bookRepository = bookRepository;
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
        ioService.outString(String.format("Updated %s book", bookRepository.saveAndFlush(book).getIsbn()));
    }

    @Override
    @Transactional
    public void add(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        bookRepository.saveAndFlush(book);
        ioService.outString(String.format("Book added. ISBN: %s", book.getIsbn()));
    }

    @Override
    @Transactional
    public void add(Book book, List<String> authors, List<String> genres) {
        Set<Author> authorList = new HashSet<>();
        Set<Genre> genreList = new HashSet<>();

        if (authors != null && authors.size() > 0) {
            for (String nameAuthor : authors) {
                Author author = authorRepository.getByFullName(nameAuthor);
                authorList.add(author != null ? author : new Author(nameAuthor));
            }
        }

        if (genres != null && genres.size() > 0) {
            for (String nameGenre : genres) {
                Genre genre = genreRepository.getGenreByName(nameGenre);
                genreList.add(genre != null ? genre : new Genre(nameGenre));
            }
        }
        book.setAuthors(authorList);
        book.setGenres(genreList);

        bookRepository.saveAndFlush(book);
    }


    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        Book book = bookRepository.getReferenceById(isbn);
        if (book == null) {
            throw new DataNotFountException(String.format("Book with ISBN %s not found", isbn));
        }
        bookRepository.deleteByIsbn(book.getIsbn());
        ioService.outString(String.format("Book deleted. ID: %s", book.getIsbn()));
    }

    @Override
    @Transactional(readOnly = true)
    public void getAll() {
        ioService.outString(printService.objectsToPrint(new HashSet<>(bookRepository.findAll())));
    }

    @Override
    @Transactional(readOnly = true)
    public void getByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new FieldRequiredException("ISBN");
        }
        ioService.outString(printService.objectToPrint(bookRepository.getReferenceById(isbn)));
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
        Book book = bookRepository.getReferenceById(isbn);
        if (book != null) {
            Genre genre = genreRepository.getGenreByName(genreName);
            if (genre == null) {
                genre = genreRepository.saveAndFlush(new Genre(genreName));
            }

            if (book.getGenres().contains(genre)) {
                throw new ObjectExistsException("The book already has an added genre");
            } else {
                book.getGenres().add(genre);
                bookRepository.saveAndFlush(book);
            }
        }
    }

    @Override
    @Transactional
    public void addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.getReferenceById(isbn);
        if (book != null) {
            Author author = authorRepository.getByFullName(fullName);
            if (author == null) {
                author = authorRepository.saveAndFlush(new Author(fullName));
            }

            if (book.getGenres().contains(author)) {
                throw new ObjectExistsException("The book already has an added author");
            } else {
                book.getAuthors().add(author);
            }
            bookRepository.saveAndFlush(book);
        }
    }

    @Override
    @Transactional
    public void addCommentToBook(String isbn, String commentText) {
        Book book = bookRepository.getReferenceById(isbn);
        if (book != null) {
            book.getComments().add(new Comment(commentText));
            bookRepository.saveAndFlush(book);
        }
    }
}
