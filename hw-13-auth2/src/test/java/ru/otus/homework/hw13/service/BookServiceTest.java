package ru.otus.homework.hw13.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import ru.otus.homework.hw13.entity.Author;
import ru.otus.homework.hw13.entity.Book;
import ru.otus.homework.hw13.entity.Comment;
import ru.otus.homework.hw13.entity.Genre;
import ru.otus.homework.hw13.entity.dto.AuthorDto;
import ru.otus.homework.hw13.entity.dto.BookDto;
import ru.otus.homework.hw13.entity.dto.CommentDto;
import ru.otus.homework.hw13.entity.dto.GenreDto;
import ru.otus.homework.hw13.mapper.BookMapper;
import ru.otus.homework.hw13.mapper.CommentMapper;
import ru.otus.homework.hw13.repository.AuthorRepository;
import ru.otus.homework.hw13.repository.BookRepository;
import ru.otus.homework.hw13.repository.CommentRepository;
import ru.otus.homework.hw13.repository.GenreRepository;
import ru.otus.homework.hw13.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса по работе с книгами")
@DataJpaTest
@Import(BookServiceImpl.class)
public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookMapper mapperBook;

    @MockBean
    private CommentMapper mapperComment;

    @MockBean
    private Authentication authentication;

    @Autowired
    private BookService bookService;

    

    @Test
    public void addTest() {
        BookDto bookActual = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов");

        given(mapperBook.toEntity(any())).willReturn(new Book());
        given(bookRepository.save(any(Book.class))).willReturn(new Book());
        given(mapperBook.toDto(any())).willReturn(bookActual);
        given(authentication.getName()).willReturn("USER");

        BookDto bookExpected = bookService.save(bookActual, authentication);
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void deleteByIdTest() {
        doNothing().when(bookRepository).deleteById(any());
        bookService.deleteByIsbn(9785040941193L);
        verify(bookRepository).deleteById(any());
    }


    @Test
    void getAllTest() {
        List<BookDto> booksActual = new ArrayList<>();
        booksActual.add(new BookDto(9785040941193L, "100 лучших сказок всех времен и народов"));
        booksActual.add(new BookDto(9785699120147L, "Колобок"));
        given(bookRepository.findAll()).willReturn(new ArrayList<>());
        given(mapperBook.toDtos(new ArrayList<>())).willReturn(booksActual);

        List<BookDto> booksExpected = bookService.getAll();
        assertEquals(booksExpected, booksActual);
    }

    @Test
    void getByIdTest() {
        BookDto bookActual = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов");
        given(bookRepository.findById(any())).willReturn(Optional.of(new Book()));
        given(mapperBook.toDto(any())).willReturn(bookActual);
        BookDto bookExpected = bookService.getByIsbn(9785040941193L);
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void getAllByAuthorTest() {
        BookDto bookActual = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов");
        bookActual.getAuthors().add(new AuthorDto(1L, "Якоб и Вильгельм Гримм"));
        bookActual.getAuthors().add(new AuthorDto(2L, "Шарль Перро"));

        given(bookRepository.findAllByAuthors_fullNameLike(any())).willReturn(Collections.singletonList(new Book()));
        given(mapperBook.toDtos(any())).willReturn(Collections.singletonList(bookActual));
        List<BookDto> bookExpected = bookService.getAllByAuthor("Гримм");
        assertEquals(bookExpected, Collections.singletonList(bookActual));
    }

    @Test
    void getAllByGenreTest() {
        BookDto bookActual = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов");
        bookActual.getGenres().add(new GenreDto(1L, "Сказки"));

        given(bookRepository.findAllByGenres_genreNameLike(any())).willReturn(Collections.singletonList(new Book()));
        given(mapperBook.toDtos(any())).willReturn(Collections.singletonList(bookActual));

        List<BookDto> bookExpected = bookService.getAllByGenre("Сказки");
        assertEquals(bookExpected, Collections.singletonList(bookActual));
    }

    @Test
    void addGenreToBook() {
        GenreDto genre = new GenreDto(1L, "Сказки");
        BookDto book = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов");

        BookDto bookActual = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов",
                new ArrayList<>(), Collections.singletonList(genre));

        given(bookRepository.findById(any())).willReturn(Optional.of(new Book()));
        given(genreRepository.findByGenreNameLike("Сказки")).willReturn(new Genre(1L, "Сказки"));
        given(bookRepository.save(any())).willReturn(new Book());
        given(mapperBook.toDto(any())).willReturn(bookActual);

        BookDto bookExpected = bookService.addGenreToBook(9785040941193L, "Сказки");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void addAuthorToBook() {
        Author author = new Author(1L, "Якоб и Вильгельм Гримм");
        AuthorDto authorDto = new AuthorDto(1L, "Якоб и Вильгельм Гримм");

        BookDto bookActual = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов",
                Collections.singletonList(authorDto), new ArrayList<>());

        given(bookRepository.findById(any())).willReturn(Optional.of(new Book()));
        given(authorRepository.findByFullNameLike("Гримм")).willReturn(author);
        given(bookRepository.save(any())).willReturn(new Book());
        given(mapperBook.toDto(any())).willReturn(bookActual);

        BookDto bookExpected = bookService.addAuthorToBook(9785040941193L, "Шарль Перро");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void addCommentToBookTest() {
        BookDto book = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов");
        CommentDto commentActual = new CommentDto(1L, "Тестирование прошло успешно", book);

        given(bookRepository.findById(any())).willReturn(Optional.of(new Book()));
        given(commentRepository.save(any())).willReturn(new Comment());
        given(mapperComment.toDto(any())).willReturn(commentActual);

        CommentDto commentExpected = bookService.addCommentToBook(9785040941193L, "Тестирование прошло успешно");
        assertEquals(commentExpected, commentActual);
    }
}
