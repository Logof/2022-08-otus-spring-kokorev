package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.PrintService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final PrintService<Book> printService;

    private final AuthorService authorService;
    private final GenreService genreService;
    private final OutputServiceStreams ioService;

    public BookServiceImpl(BookRepository bookRepository,
                           PrintService<Book> printService,
                           AuthorService authorService,
                           GenreService genreService, OutputServiceStreams ioService) {
        this.bookRepository = bookRepository;
        this.printService = printService;
        this.authorService = authorService;
        this.genreService = genreService;
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
    public void add(Book book, List<String> authors, List<String> genres) {
        List<Author> authorList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        if (authors != null && authors.size() > 0) {
            for (String nameAuthor : authors) {
                Author author = authorService.findByAuthorFullName(nameAuthor);
                authorList.add(author != null ? author : new Author(null, nameAuthor));
            }
        }

        if (genres != null && genres.size() > 0) {
            for (String nameGenre : genres) {
                Genre genre = genreService.findByGenreName(nameGenre);
                genreList.add( genre != null ? genre : new Genre(null, nameGenre));
            }
        }
        book.setAuthors(authorList);
        book.setGenres(genreList);

        bookRepository.insert(book);
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

}
