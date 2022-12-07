package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Genre;
import ru.otus.homework.hw08.exception.DataNotFountException;
import ru.otus.homework.hw08.exception.FieldRequiredException;
import ru.otus.homework.hw08.exception.ObjectExistsException;
import ru.otus.homework.hw08.repository.AuthorRepository;
import ru.otus.homework.hw08.repository.BookRepository;
import ru.otus.homework.hw08.repository.GenreRepository;
import ru.otus.homework.hw08.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

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
        List<Book> bookList =  bookRepository.findAll();
        return bookList;
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
        return bookRepository.findAllByAuthors_fullNameLike(fullName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllByGenre(String genreName) {
        return bookRepository.findAllByGenres_genreNameLike(genreName);
    }

    @Override
    @Transactional
    public Book addGenreToBook(String isbn, String genreName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Genre genre = genreRepository.findByGenreNameLike(genreName);

        if (genre != null && (book.getGenres().stream()
                .filter(g -> g.getGenreName().equals(genreName))
                .collect(Collectors.toList()).size() != 0
                || genre.getBookList().stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .collect(Collectors.toList()).size() != 0)) {
            throw new ObjectExistsException("The book already has an added genre");
        }

        if (Objects.isNull(genre)) {
            genre = new Genre(genreName, new ArrayList<>());
        }
        genre.getBookList().add(book);
        book.getGenres().add(genre);

        genreRepository.save(genre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Author author = authorRepository.findByFullNameLike(fullName);

        if (author != null && (
                book.getAuthors().stream().filter(a -> a.getFullName().equals(fullName)).collect(Collectors.toList()).size() != 0
                || author.getBookList().stream().filter(b -> b.getIsbn().equals(isbn)).collect(Collectors.toList()).size() !=0)) {
            throw new ObjectExistsException("The book already has an added author");
        }

        if (Objects.isNull(author)) {
            author = new Author(fullName);
        }
        author.getBookList().add(book);
        book.getAuthors().add(author);

        authorRepository.save(author);
        return bookRepository.save(book);

    }
}
