package ru.otus.homework.hw11.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.hw11.entity.dto.BookDto;
import ru.otus.homework.hw11.entity.dto.CommentDto;
import ru.otus.homework.hw11.service.BookService;
import ru.otus.homework.hw11.service.CommentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllBookTest() throws Exception {
        List<BookDto> bookDtoList = new ArrayList<>();
        given(bookService.getAll()).willReturn(bookDtoList);

        mvc.perform(get("/api/book").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getBookByIsbnTest() throws Exception {
        given(bookService.getByIsbn(anyString())).willReturn(new BookDto());
        given(commentService.getAllByIsbn(anyString())).willReturn(Collections.singletonList(new CommentDto()));

        mvc.perform(get("/api/book/123-123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveBookTest() throws Exception {
        mvc.perform(post("/api/book/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new BookDto("123-123", "Title"))))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBookTest() throws Exception {
        mvc.perform(delete("/api/book/123-123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
