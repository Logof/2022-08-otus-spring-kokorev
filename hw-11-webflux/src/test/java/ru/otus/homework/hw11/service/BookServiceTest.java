package ru.otus.homework.hw11.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homework.hw11.entity.Author;
import ru.otus.homework.hw11.entity.Book;
import ru.otus.homework.hw11.entity.Comment;
import ru.otus.homework.hw11.entity.Genre;
import ru.otus.homework.hw11.exception.FieldRequiredException;
import ru.otus.homework.hw11.repository.AuthorRepository;
import ru.otus.homework.hw11.repository.BookRepository;
import ru.otus.homework.hw11.repository.CommentRepository;
import ru.otus.homework.hw11.repository.GenreRepository;
import ru.otus.homework.hw11.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

    @Autowired
    private BookService bookService;

    @Test
    public void updateTitleTest() {
        Book Book = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");

        given(bookRepository.findById(anyString())).willReturn(Optional.of(new Book()));
        given(bookRepository.save(any(Book.class))).willReturn(new Book());


        Book bookActual = bookService.getByIsbn("978-5-04-094119-3");
        bookActual.setTitle("1000 лучших сказок всех времен и народов");
        Book bookExpected = bookService.updateTitle("978-5-04-094119-3", "1000 лучших сказок всех времен и народов");
        assertEquals(bookExpected, bookActual);

        Exception exceptionNull = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle("978-5-04-094119-3", null);
        });
        String expectedMessage = "Required field(s): isbn, title";
        assertTrue(exceptionNull.getMessage().contains(expectedMessage));

        Exception exceptionEmpty = assertThrows(FieldRequiredException.class, () -> {
            bookService.updateTitle("978-5-04-094119-3", "");
        });
        assertTrue(exceptionEmpty.getMessage().contains(expectedMessage));
    }

    @Test
    public void addTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");

        given(mapperBook.toEntity(any())).willReturn(new Book());
        given(bookRepository.save(any(Book.class))).willReturn(new Book());
        given(mapperBook.toDto(any())).willReturn(bookActual);

        Book bookExpected = bookService.save(bookActual);
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void deleteByIdTest() {
        doNothing().when(bookRepository).deleteById(anyString());
        bookService.deleteByIsbn("978-5-04-094119-3");
        verify(bookRepository).deleteById(anyString());
    }


    @Test
    void getAllTest() {
        List<Book> booksActual = new ArrayList<>();
        booksActual.add(new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов"));
        booksActual.add(new Book("978-5-699-12014-7", "Колобок"));
        given(bookRepository.findAll()).willReturn(new ArrayList<>());
        given(mapperBook.toDtos(new ArrayList<>())).willReturn(booksActual);

        List<Book> booksExpected = bookService.getAll();
        assertEquals(booksExpected, booksActual);
    }

    @Test
    void getByIdTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        given(bookRepository.findById(anyString())).willReturn(Optional.of(new Book()));
        given(mapperBook.toDto(any())).willReturn(bookActual);
        Book bookExpected = bookService.getByIsbn("978-5-04-094119-3");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void getAllByAuthorTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        bookActual.getAuthors().add(new Author(1L, "Якоб и Вильгельм Гримм"));
        bookActual.getAuthors().add(new Author(2L, "Шарль Перро"));

        given(bookRepository.findAllByAuthors_fullNameLike(any())).willReturn(Collections.singletonList(new Book()));
        given(mapperBook.toDtos(any())).willReturn(Collections.singletonList(bookActual));
        List<Book> bookExpected = bookService.getAllByAuthor("Гримм");
        assertEquals(bookExpected, Collections.singletonList(bookActual));
    }

    @Test
    void getAllByGenreTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        bookActual.getGenres().add(new Genre(1L, "Сказки"));

        given(bookRepository.findAllByGenres_genreNameLike(any())).willReturn(Collections.singletonList(new Book()));
        given(mapperBook.toDtos(any())).willReturn(Collections.singletonList(bookActual));

        List<Book> bookExpected = bookService.getAllByGenre("Сказки");
        assertEquals(bookExpected, Collections.singletonList(bookActual));
    }

    @Test
    void addGenreToBook() {
        Genre genre = new Genre(1L, "Сказки");
        Book book = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");

        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов",
                new ArrayList<>(), Collections.singletonList(genre));

        given(bookRepository.findById(anyString())).willReturn(Optional.of(new Book()));
        given(genreRepository.findByGenreNameLike("Сказки")).willReturn(new Genre(1L, "Сказки"));
        given(bookRepository.save(any())).willReturn(new Book());
        given(mapperBook.toDto(any())).willReturn(bookActual);

        Book bookExpected = bookService.addGenreToBook("978-5-04-094119-3", "Сказки");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void addAuthorToBook() {
        Author author = new Author(1L, "Якоб и Вильгельм Гримм");
        Author Author = new Author(1L, "Якоб и Вильгельм Гримм");

        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов",
                Collections.singletonList(Author), new ArrayList<>());

        given(bookRepository.findById(anyString())).willReturn(Optional.of(new Book()));
        given(authorRepository.findByFullNameLike("Гримм")).willReturn(author);
        given(bookRepository.save(any())).willReturn(new Book());
        given(mapperBook.toDto(any())).willReturn(bookActual);

        Book bookExpected = bookService.addAuthorToBook("978-5-04-094119-3", "Шарль Перро");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void addCommentToBookTest() {
        Book book = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        Comment commentActual = new Comment(1L, "Тестирование прошло успешно", book);

        given(bookRepository.findById(anyString())).willReturn(Optional.of(new Book()));
        given(commentRepository.save(any())).willReturn(new Comment());
        given(mapperComment.toDto(any())).willReturn(commentActual);

        Comment commentExpected = bookService.addCommentToBook("978-5-04-094119-3", "Тестирование прошло успешно");
        assertEquals(commentExpected, commentActual);
    }
}
