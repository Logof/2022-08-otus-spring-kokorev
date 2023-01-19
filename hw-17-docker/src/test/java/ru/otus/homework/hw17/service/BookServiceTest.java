package ru.otus.homework.hw17.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import ru.otus.homework.hw17.dto.BookDto;
import ru.otus.homework.hw17.entity.Book;
import ru.otus.homework.hw17.mapper.BookMapper;
import ru.otus.homework.hw17.repository.AuthorRepository;
import ru.otus.homework.hw17.repository.BookRepository;
import ru.otus.homework.hw17.repository.CommentRepository;
import ru.otus.homework.hw17.repository.GenreRepository;
import ru.otus.homework.hw17.service.impl.BookServiceImpl;

import java.util.ArrayList;
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
    private Authentication authentication;

    @MockBean
    private PermissionService<Book> permissionService;

    @Autowired
    private BookService bookService;

    

    @Test
    public void addTest() {
        Book bookActual = new Book(9785040941193L, "100 лучших сказок всех времен и народов",
                new ArrayList<>(), new ArrayList<>());
        BookDto bookActualDto = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов",
                new ArrayList<>(), new ArrayList<>());

        given(mapperBook.toEntity(any())).willReturn(bookActual);
        given(bookRepository.save(any(Book.class))).willReturn(bookActual);
        given(mapperBook.toDto(any())).willReturn(bookActualDto);
        given(authentication.getName()).willReturn("admin");

        Book bookExpected = bookService.save(bookActual, authentication);
        assertEquals(bookExpected, bookActual);
    }

    @Test
    void deleteByIdTest() {
        doNothing().when(bookRepository).deleteById(any());
        bookService.deleteById(9785040941193L);
        verify(bookRepository).deleteById(any());
    }


    @Test
    void getAllTest() {
        List<BookDto> booksActual = new ArrayList<>();
        booksActual.add(new BookDto(9785040941193L, "100 лучших сказок всех времен и народов", new ArrayList<>(), new ArrayList<>()));
        booksActual.add(new BookDto(9785699120147L, "Колобок", new ArrayList<>(), new ArrayList<>()));
        given(bookRepository.findAll()).willReturn(new ArrayList<>());
        given(mapperBook.toDtos(new ArrayList<>())).willReturn(booksActual);

        List<BookDto> booksExpected = bookService.getAll();
        assertEquals(booksExpected, booksActual);
    }

    @Test
    void getByIdTest() {
        BookDto bookActual = new BookDto(9785040941193L, "100 лучших сказок всех времен и народов", new ArrayList<>(), new ArrayList<>());
        given(bookRepository.findById(any())).willReturn(Optional.of(new Book()));
        given(mapperBook.toDto(any())).willReturn(bookActual);
        BookDto bookExpected = bookService.getByIsbn(9785040941193L);
        assertEquals(bookExpected, bookActual);
    }
}
