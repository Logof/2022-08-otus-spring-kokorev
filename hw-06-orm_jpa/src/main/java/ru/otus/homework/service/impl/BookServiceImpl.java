package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.exception.DataNotFountException;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.GenreRepository;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.print.PrintService;

import java.util.ArrayList;
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
    @Transactional
    public void updateTitle(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            //todo Заменить на thorw
            ioService.outString("Updated 0 book(s)");
        } else {
            ioService.outString(String.format("Updated %s book", bookRepository.update(book).getIsbn()));
        }
    }

    @Override
    @Transactional
    public void add(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            //todo Заменить на thorw
            ioService.outString("");
        }
        bookRepository.insert(book);
        ioService.outString(String.format("Book added. ISBN: %s", book.getIsbn()));
    }

    @Override
    @Transactional
    public void add(Book book, List<String> authors, List<String> genres) {
        List<Author> authorList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        if (authors != null && authors.size() > 0) {
            for (String nameAuthor : authors) {
                Author author = authorRepository.getByFullName(nameAuthor);
                authorList.add(author != null ? author : new Author(nameAuthor));
            }
        }

        if (genres != null && genres.size() > 0) {
            for (String nameGenre : genres) {
                Genre genre = genreRepository.getGenreByName(nameGenre);
                genreList.add( genre != null ? genre : new Genre(nameGenre));
            }
        }
        book.setAuthors(authorList);
        book.setGenres(genreList);

        bookRepository.insert(book);
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
            //TODO Написать сообщение
            throw new DataNotFountException("");
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

            if (book.getGenres().indexOf(genre) != 0) {
                //TODO выкинуть ошибку
            } else {
                book.getGenres().add(genre);
            }
        }
    }

    @Override
    public void addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.getBookByIsbn(isbn);
        if (book != null) {
            Author author = authorRepository.getByFullName(fullName);
            if (author == null) {
                author = authorRepository.save(new Author(fullName));
            }

            if (book.getGenres().indexOf(author) != 0) {
                //TODO выкинуть ошибку
            } else {
                book.getAuthors().add(author);
            }
        }
    }
}
