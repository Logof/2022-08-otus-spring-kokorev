package ru.otus.homework.hw12.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw12.entity.Author;
import ru.otus.homework.hw12.entity.Book;
import ru.otus.homework.hw12.entity.Comment;
import ru.otus.homework.hw12.entity.Genre;
import ru.otus.homework.hw12.entity.dto.BookDto;
import ru.otus.homework.hw12.entity.dto.CommentDto;
import ru.otus.homework.hw12.exception.DataNotFountException;
import ru.otus.homework.hw12.exception.FieldRequiredException;
import ru.otus.homework.hw12.exception.ObjectExistsException;
import ru.otus.homework.hw12.mapper.BookMapper;
import ru.otus.homework.hw12.mapper.CommentMapper;
import ru.otus.homework.hw12.repository.AuthorRepository;
import ru.otus.homework.hw12.repository.BookRepository;
import ru.otus.homework.hw12.repository.CommentRepository;
import ru.otus.homework.hw12.repository.GenreRepository;
import ru.otus.homework.hw12.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    private final BookMapper mapperBook;

    private final CommentMapper mapperComment;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           CommentRepository commentRepository, BookMapper mapperBook, CommentMapper mapperComment) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.mapperBook = mapperBook;
        this.mapperComment = mapperComment;
    }

    @Override
    @Transactional
    public BookDto updateTitle(String isbn, String newTitle) {
        if (isbn == null || isbn.isBlank() || newTitle == null || newTitle.isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        book.setTitle(newTitle);
        return mapperBook.toDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookDto save(BookDto book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        return mapperBook.toDto(bookRepository.save(mapperBook.toEntity(book)));
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        return mapperBook.toDtos(bookRepository.findAllByOrderByTitleAsc());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new FieldRequiredException("ISBN");
        }
        return mapperBook.toDto(bookRepository.findById(isbn)
                .orElseThrow(() -> new DataNotFountException("Book not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllByAuthor(String fullName) {
        return mapperBook.toDtos(bookRepository.findAllByAuthors_fullNameLike("%" + fullName + "%"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllByGenre(String genreName) {
        return mapperBook.toDtos(bookRepository.findAllByGenres_genreNameLike("%" + genreName + "%"));
    }

    @Override
    @Transactional
    public BookDto addGenreToBook(String isbn, String genreName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Genre genre = genreRepository.findByGenreNameLike(genreName);

        if (genre != null && book.getGenres().contains(genre)) {
            throw new ObjectExistsException("The book already has an added genre");
        }
        book.getGenres().add(genre != null ? genre : new Genre(null, genreName));

        return mapperBook.toDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookDto addAuthorToBook(String isbn, String fullName) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        Author author = authorRepository.findByFullNameLike(fullName);

        if (author != null && book.getAuthors().contains(author)) {
            throw new ObjectExistsException("The book already has an added author");
        }
        book.getAuthors().add(author != null ? author : new Author(null, fullName));

        return mapperBook.toDto(bookRepository.save(book));

    }

    @Override
    @Transactional
    public CommentDto addCommentToBook(String isbn, String commentText) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        if (commentText == null || commentText.isEmpty()) {
            throw new FieldRequiredException("commentText");
        }
        return mapperComment.toDto(commentRepository.save(new Comment(null, commentText, book)));
    }
}
