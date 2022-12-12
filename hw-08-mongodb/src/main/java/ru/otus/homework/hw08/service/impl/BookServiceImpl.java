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

import java.util.List;

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
    public void updateTitle(String isbn, String newTitle) {
        if (isbn == null || isbn.isBlank() || newTitle == null || newTitle.isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        bookRepository.updateDocumentTitle(isbn, newTitle);
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
        Author author = authorRepository.findByFullNameLike(fullName)
                .orElseThrow(() -> new DataNotFountException("Author not found"));
        return bookRepository.findAllByAuthors(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllByGenre(String genreName) {
        Genre genre = genreRepository.findByGenreNameLike(genreName)
                .orElseThrow(() -> new DataNotFountException("Genre not found"));
        return bookRepository.findAllByGenres(genre);
    }

    @Override
    @Transactional
    public Book addGenreToBook(String isbn, String genreName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Genre genre = genreRepository.findByGenreNameLike(genreName)
                .orElse(genreRepository.save(new Genre(genreName)));

        if (book.getGenres().stream()
                .filter(g -> g.getGenreName().equals(genreName)).count() != 0) {
            throw new ObjectExistsException("The book already has an added genre");
        }

        book.getGenres().add(genre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Author author = authorRepository.findByFullNameLike(fullName)
                .orElse(authorRepository.save(new Author(fullName)));

        if (book.getAuthors().stream()
                .filter(a -> a.getFullName().equals(fullName)).count() != 0) {
            throw new ObjectExistsException("The book already has an added author");
        }

        book.getAuthors().add(author);
        return bookRepository.save(book);

    }
}
