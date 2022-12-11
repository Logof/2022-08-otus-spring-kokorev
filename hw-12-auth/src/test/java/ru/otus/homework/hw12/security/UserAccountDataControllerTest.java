package ru.otus.homework.hw12.security;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.homework.hw12.entity.dto.BookDto;
import ru.otus.homework.hw12.service.BookService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithAnonymousUser
    public void unauthorizedUserAccessTest() throws Exception
    {
        when(bookService.getByIsbn(anyString())).thenReturn(new BookDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.ALL))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/book?isbn=123").accept(MediaType.ALL))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(MockMvcRequestBuilders.get("/edit?isbn=123").accept(MediaType.ALL))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(MockMvcRequestBuilders.get("/new").accept(MediaType.ALL))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(MockMvcRequestBuilders.get("/delete").accept(MediaType.ALL))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "Test", password = "123456", roles = {"ADMIN"})
    public void authorizedAdminAccessTest() throws Exception
    {
        when(bookService.getByIsbn(anyString())).thenReturn(new BookDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/book?isbn=123").accept(MediaType.ALL))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/edit?isbn=123").accept(MediaType.ALL))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/new").accept(MediaType.ALL))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/delete").accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Test", password = "123456", roles = {"USER"})
    public void authorizedUserAccessTest() throws Exception
    {
        when(bookService.getByIsbn(anyString())).thenReturn(new BookDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/book?isbn=123").accept(MediaType.ALL))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/edit?isbn=123").accept(MediaType.ALL))
                .andExpect(status().is4xxClientError());
        mockMvc.perform(MockMvcRequestBuilders.get("/new").accept(MediaType.ALL))
                .andExpect(status().is4xxClientError());
        mockMvc.perform(MockMvcRequestBuilders.get("/delete").accept(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }

}
