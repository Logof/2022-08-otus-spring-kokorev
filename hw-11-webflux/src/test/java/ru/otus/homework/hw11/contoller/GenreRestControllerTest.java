package ru.otus.homework.hw11.contoller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.hw11.entity.dto.GenreDto;
import ru.otus.homework.hw11.service.GenreService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreRestController.class)
public class GenreRestControllerTest {
    @MockBean
    private GenreService genreService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllAuthorsTest() throws Exception {
        List<GenreDto> genreDtos = new ArrayList<>();
        given(genreService.getAll()).willReturn(genreDtos);

        mvc.perform(get("/api/genre").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
