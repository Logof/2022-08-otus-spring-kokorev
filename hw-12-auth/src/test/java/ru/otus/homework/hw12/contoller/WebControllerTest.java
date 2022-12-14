package ru.otus.homework.hw12.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.hw12.entity.dto.AuthorDto;
import ru.otus.homework.hw12.entity.dto.BookDto;
import ru.otus.homework.hw12.entity.dto.CommentDto;
import ru.otus.homework.hw12.entity.dto.GenreDto;
import ru.otus.homework.hw12.service.AuthorService;
import ru.otus.homework.hw12.service.BookService;
import ru.otus.homework.hw12.service.CommentService;
import ru.otus.homework.hw12.service.GenreService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WebControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void indexPageBookTest() throws Exception {
        List<BookDto> bookDtoList = new ArrayList<>();
        given(bookService.getAll()).willReturn(bookDtoList);

        mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void viewPageBookTest() throws Exception {
        given(bookService.getByIsbn(anyString())).willReturn(new BookDto());
        given(commentService.getAllByIsbn(anyString())).willReturn(Collections.singletonList(new CommentDto()));

        mvc.perform(get("/book?isbn=123-123").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void newPageBookTest() throws Exception {
        given(authorService.getAll()).willReturn(Collections.singletonList(new AuthorDto()));
        given(genreService.getAll()).willReturn(Collections.singletonList(new GenreDto()));

        mvc.perform(get("/new").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void editPageBookTest() throws Exception {
        given(bookService.getByIsbn(anyString())).willReturn(new BookDto("123-123", "Title"));
        given(authorService.getAll()).willReturn(Collections.singletonList(new AuthorDto()));
        given(genreService.getAll()).willReturn(Collections.singletonList(new GenreDto()));

        mvc.perform(get("/edit?isbn=123-123").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePageBookTest() throws Exception {
        mvc.perform(get("/delete?isbn=123-123").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void saveBookTest() throws Exception {
        mvc.perform(post("/book").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void deleteBookTest() throws Exception {
        mvc.perform(post("/delete").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().is3xxRedirection());
    }
}
