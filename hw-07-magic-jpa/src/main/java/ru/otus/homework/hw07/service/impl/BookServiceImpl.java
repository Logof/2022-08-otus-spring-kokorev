package ru.otus.homework.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.entity.Book;
import ru.otus.homework.hw07.entity.Comment;
import ru.otus.homework.hw07.entity.Genre;
import ru.otus.homework.hw07.entity.dto.BookDto;
import ru.otus.homework.hw07.exception.DataNotFountException;
import ru.otus.homework.hw07.exception.FieldRequiredException;
import ru.otus.homework.hw07.exception.ObjectExistsException;
import ru.otus.homework.hw07.mapper.BookMapper;
import ru.otus.homework.hw07.repository.AuthorRepository;
import ru.otus.homework.hw07.repository.BookRepository;
import ru.otus.homework.hw07.repository.CommentRepository;
import ru.otus.homework.hw07.repository.GenreRepository;
import ru.otus.homework.hw07.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    private final BookMapper mapper;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           CommentRepository commentRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public BookDto updateTitle(String isbn, String newTitle) {
        if (isbn == null || isbn.isBlank() || newTitle == null || newTitle.isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        book.setTitle(newTitle);
        return mapper.toDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookDto add(BookDto book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank() || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new FieldRequiredException("isbn", "title");
        }
        //TODO много конвертаций
        return mapper.toDto(bookRepository.save(mapper.toEntity(book)));
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(book -> mapper.toDto(book)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new FieldRequiredException("ISBN");
        }
        return mapper.toDto(bookRepository.findById(isbn)
                .orElseThrow(() -> new DataNotFountException("Book not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllByAuthor(String fullName) {
        //Author author = authorRepository.findByFullNameLike("%" + fullName + "%");
        return bookRepository.findAllByAuthors_fullName(fullName).stream()
                .map(book -> mapper.toDto(book)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllByGenre(String genreName) {
        //Genre genre = genreRepository.findByGenreNameLike("%" + genreName + "%");
        return bookRepository.findAllByGenres_genreNameLike(genreName).stream()
                .map(book -> mapper.toDto(book)).collect(Collectors.toList());
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

        return mapper.toDto(bookRepository.save(book));
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

        return mapper.toDto(bookRepository.save(book));

    }

    @Override
    @Transactional
    public Comment addCommentToBook(String isbn, String commentText) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        if (commentText == null || commentText.isEmpty()) {
            throw new FieldRequiredException("commentText");
        }
        return commentRepository.save(new Comment(null, commentText, book));
    }
}
