package ru.otus.homework.hw11.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.hw11.entity.dto.AuthorDto;
import ru.otus.homework.hw11.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorRestController.class)
public class AuthorRestControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllAuthorsTest() throws Exception {
        List<AuthorDto> authorDtos = new ArrayList<>();
        given(authorService.getAll()).willReturn(authorDtos);

        mvc.perform(get("/api/author").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
