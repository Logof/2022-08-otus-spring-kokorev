package ru.otus.homework.hw05.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw05.entity.Author;
import ru.otus.homework.hw05.entity.Book;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.exception.DataNotFountException;
import ru.otus.homework.hw05.repository.AuthorRepository;
import ru.otus.homework.hw05.repository.BookRepository;
import ru.otus.homework.hw05.repository.GenreRepository;
import ru.otus.homework.hw05.service.BookService;
import ru.otus.homework.hw05.service.print.PrintService;

import java.util.List;

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
    public void updateTitle(Book book) {
        ioService.outString(String.format("Updated %d book(s)", bookRepository.update(book)));
    }

    @Override
    public void add(Book book) {
        int rowAddCount = bookRepository.insert(book);
        ioService.outString(rowAddCount == 1 ? String.format("Book added. ISBN: %s", book.getIsbn())
                : String.format("Added %d book(s) by ISBN %s ", rowAddCount, book.getIsbn()));
    }

    @Override
    public void deleteById(String isbn) {
        if (bookRepository.getBookById(isbn) == null) {
            throw new DataNotFountException(String.format("Book with ISBN %s not found", isbn));
        }
        bookRepository.delete(isbn);
        ioService.outString(String.format("Book deleted. ID: %s", isbn));
    }

    @Override
    public void getAll() {
        List<Book> books = bookRepository.getAll();
        ioService.outString(printService.objectsToPrint(books));
    }

    @Override
    public void getById(String isbn) {
        ioService.outString(printService.objectToPrint(bookRepository.getBookById(isbn)));
    }

    @Override
    public void getAllByAuthor(String fullName) {
        ioService.outString(printService.objectsToPrint(bookRepository.getAllByAuthor(fullName)));
    }

    @Override
    public void getAllByGenre(String genreName) {
        ioService.outString(printService.objectsToPrint(bookRepository.getAllByGenre(genreName)));
    }

    @Override
    public void addGenreToBook(String isbn, String genreName) {
        Genre genre = genreRepository.getGenreByName(genreName);
        if (genre == null) {
            genre = genreRepository.insert(new Genre(genreName));
        }

        if (!genreRepository.genreHasBooks(genre.getId())) {
            bookRepository.addGenreToBook(isbn, genre.getId());
        }
    }

    @Override
    public void addAuthorToBook(String isbn, String fullName) {
        Author author = authorRepository.getByFullName(fullName);
        if (author == null) {
            author = authorRepository.insert(new Author(fullName));
        }
        if (!authorRepository.authorHasBooks(author.getId())) {
            bookRepository.addAuthorToBook(isbn, author.getId());
        }
    }

}
