package ru.otus.homework.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.entity.Book;
import ru.otus.homework.hw07.entity.Comment;
import ru.otus.homework.hw07.entity.Genre;
import ru.otus.homework.hw07.exception.DataNotFountException;
import ru.otus.homework.hw07.exception.FieldRequiredException;
import ru.otus.homework.hw07.exception.ObjectExistsException;
import ru.otus.homework.hw07.repository.AuthorRepository;
import ru.otus.homework.hw07.repository.BookRepository;
import ru.otus.homework.hw07.repository.CommentRepository;
import ru.otus.homework.hw07.repository.GenreRepository;
import ru.otus.homework.hw07.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
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
    //@Transactional(readOnly = true)
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
        Author author = authorRepository.findByFullNameLike("%" + fullName + "%");
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllByGenre(String genreName) {
        Genre genre = genreRepository.findByGenreNameLike("%" + genreName + "%");
        return bookRepository.findAllByGenre(genre);
    }

    @Override
    @Transactional
    public Book addGenreToBook(String isbn, String genreName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Genre genre = genreRepository.findByGenreNameLike(genreName);

        if (genre != null && book.getGenres().contains(genre)) {
            throw new ObjectExistsException("The book already has an added genre");
        }
        book.getGenres().add(genre != null ? genre : new Genre(genreName));

        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Author author = authorRepository.findByFullNameLike(fullName);

        if (author != null && book.getAuthors().contains(author)) {
            throw new ObjectExistsException("The book already has an added author");
        }
        book.getAuthors().add(author != null ? author : new Author(fullName));

        return bookRepository.save(book);

    }

    @Override
    @Transactional
    public Comment addCommentToBook(String isbn, String commentText) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        if (commentText == null || commentText.isEmpty()) {
            throw new FieldRequiredException("commentText");
        }
        return commentRepository.save(new Comment(commentText, book));
    }
}
