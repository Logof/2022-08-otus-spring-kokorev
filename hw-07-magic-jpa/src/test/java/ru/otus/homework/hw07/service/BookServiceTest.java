package ru.otus.homework.hw07.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.entity.Book;
import ru.otus.homework.hw07.entity.Comment;
import ru.otus.homework.hw07.entity.Genre;
import ru.otus.homework.hw07.exception.FieldRequiredException;
import ru.otus.homework.hw07.repository.AuthorRepository;
import ru.otus.homework.hw07.repository.BookRepository;
import ru.otus.homework.hw07.repository.CommentRepository;
import ru.otus.homework.hw07.repository.GenreRepository;
import ru.otus.homework.hw07.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@DisplayName("Тест сервиса по работе с книгами")
@DataJpaTest
@Import(BookServiceImpl.class)
public class BookServiceTest {

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    GenreRepository genreRepository;

    @MockBean
    CommentRepository commentRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void updateTitleTest() {
        Book book1 = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        Book book2 = new Book("978-5-04-094119-3", "1000 лучших сказок всех времен и народов");
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book1));
        when(bookRepository.save(any(Book.class))).thenReturn(book2);

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
        when(bookRepository.save(any(Book.class))).thenReturn(bookActual);
        Book bookExpected = bookService.add(bookActual);
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
        when(bookRepository.findAll()).thenReturn(booksActual);

        List<Book> booksExpected = bookService.getAll();
        assertEquals(booksExpected, booksActual);
    }

    @Test
    void getByIdTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(bookActual));
        Book bookExpected = bookService.getByIsbn("978-5-04-094119-3");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void getAllByAuthorTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        bookActual.getAuthors().add(new Author(1L, "Якоб и Вильгельм Гримм"));
        bookActual.getAuthors().add(new Author(2L, "Шарль Перро"));

        when(bookRepository.findAllByAuthor(any())).thenReturn(Collections.singletonList(bookActual));

        List<Book> bookExpected = bookService.getAllByAuthor("Гримм");
        assertEquals(bookExpected, Collections.singletonList(bookActual));
    }

    @Test
    void getAllByGenreTest() {
        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        bookActual.getGenres().add(new Genre(1L, "Сказки"));

        when(bookRepository.findAllByGenre(any())).thenReturn(Collections.singletonList(bookActual));

        List<Book> bookExpected = bookService.getAllByGenre("Сказки");
        assertEquals(bookExpected, Collections.singletonList(bookActual));
    }

    @Test
    void addGenreToBook() {
        Genre genre = new Genre(1L, "Сказки");
        Book book = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");

        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов",
                new ArrayList<>(), Collections.singletonList(genre));

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));
        when(genreRepository.findByGenreNameLike("Сказки")).thenReturn(genre);
        when(bookRepository.save(any())).thenReturn(bookActual);

        Book bookExpected = bookService.addGenreToBook("978-5-04-094119-3", "Сказки");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void addAuthorToBook() {
        Author author = new Author(1L, "Якоб и Вильгельм Гримм");
        Book book = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");

        Book bookActual = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов",
                Collections.singletonList(author), new ArrayList<>());

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));
        when(authorRepository.findByFullNameLike("Сказки")).thenReturn(author);
        when(bookRepository.save(any())).thenReturn(bookActual);

        Book bookExpected = bookService.addAuthorToBook("978-5-04-094119-3", "Шарль Перро");
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void addCommentToBookTest() {
        Book book = new Book("978-5-04-094119-3", "100 лучших сказок всех времен и народов");
        Comment commentActual = new Comment(1L, "Тестирование прошло успешно", book);

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));
        when(commentRepository.save(any())).thenReturn(commentActual);

        Comment commentExpected = bookService.addCommentToBook("978-5-04-094119-3", "Тестирование прошло успешно");
        assertEquals(commentExpected, commentActual);
    }
}
