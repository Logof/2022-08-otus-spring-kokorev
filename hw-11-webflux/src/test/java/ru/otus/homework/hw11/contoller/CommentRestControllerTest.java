package ru.otus.homework.hw11.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.hw11.entity.dto.CommentDto;
import ru.otus.homework.hw11.service.CommentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentRestController.class)
public class CommentRestControllerTest {

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllCommentByIsbnTest() throws Exception {
        List<CommentDto> commentDtos = new ArrayList<>();
        given(commentService.getAllByIsbn(anyString())).willReturn(commentDtos);

        mvc.perform(get("/api/comment/123-123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
