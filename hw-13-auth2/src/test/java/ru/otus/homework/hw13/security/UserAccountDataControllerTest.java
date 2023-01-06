package ru.otus.homework.hw13.security;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.hw13.dto.BookDto;
import ru.otus.homework.hw13.service.AuthorService;
import ru.otus.homework.hw13.service.BookService;
import ru.otus.homework.hw13.service.GenreService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    @WithAnonymousUser
    public void unauthorizedUserAccessTest() throws Exception
    {
        when(bookService.getAll()).thenReturn(new ArrayList<>());
        when(bookService.getByIsbn(anyLong())).thenReturn(new BookDto());

        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/book?isbn=9785040941193"))
                .andExpect(redirectedUrl("http://localhost/login")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/new"))
                .andExpect(redirectedUrl("http://localhost/login")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/denied"))
                .andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/delete?isbn=9785040941193"))
                .andExpect(redirectedUrl("http://localhost/login")).andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/book").accept(MediaType.ALL))
                .andExpect(redirectedUrl("http://localhost/login")).andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/delete?isbn=9785040941193").accept(MediaType.ALL))
                .andExpect(redirectedUrl("http://localhost/login")).andExpect(status().is3xxRedirection());
    }


    @Test
    @WithMockUser(authorities = {"ROLE_READER"})
    public void readerAccessTest() throws Exception
    {
        when(bookService.getAll()).thenReturn(new ArrayList<>());
        when(bookService.getByIsbn(anyLong())).thenReturn(new BookDto());

        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/book?isbn=9785040941193")).andExpect(status().isOk());
        mockMvc.perform(get("/new"))
                .andExpect(redirectedUrl("/denied")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/denied"))
                .andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/delete?isbn=9785040941193"))
                .andExpect(redirectedUrl("/denied")).andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/book").accept(MediaType.ALL))
                .andExpect(redirectedUrl("/denied")).andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/delete?isbn=9785040941193").accept(MediaType.ALL))
                .andExpect(redirectedUrl("/denied")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_EDITOR"})
    public void editorAccessTest() throws Exception
    {
        when(bookService.getAll()).thenReturn(new ArrayList<>());
        when(bookService.getByIsbn(anyLong())).thenReturn(new BookDto());
        when(authorService.getAll()).thenReturn(new ArrayList<>());
        when(genreService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/book?isbn=9785040941193")).andExpect(status().isOk());
        mockMvc.perform(get("/new")).andExpect(status().isOk());
        mockMvc.perform(get("/denied"))
                .andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/delete?isbn=9785040941193"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/book").accept(MediaType.ALL))
                .andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/delete?isbn=9785040941193").accept(MediaType.ALL))
                .andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
    }
}