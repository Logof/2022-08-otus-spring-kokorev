package ru.otus.homework.hw13;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.hw13.entity.Book;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "user1")
    public void user1ShouldGetDocument1() throws Exception {

        Book d1=new Book(1L, "Document 1");
        mockMvc.perform(
                get("/document"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(d1))));

    }

    @Test
    @WithMockUser(username = "user2")
    public void user2ShouldGetDocument2() throws Exception {

        Book d2=new Book(2L, "Document 2");
        mockMvc.perform(
                get("/document"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(d2))));
        ;
    }

    @Test
    @WithMockUser(username = "admin")
    public void adminShouldGetDocuments123() throws Exception {

        Book d1=new Book(1L, "Document 1");
        Book d2=new Book(2L, "Document 2");
        Book d3=new Book(3L, "Document 3");

        mockMvc.perform(
                get("/document"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(d1,d2,d3))));
        ;
    }

    @Test
    @WithMockUser(username = "user1")
    public void user1ShouldGetDocument1WithPathVariable() throws Exception {

        Book d1=new Book(1L, "Document 1");
        mockMvc.perform(
                get("/document/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(d1)));

    }

    @Test
    @WithMockUser(username = "user1")
    public void user1ShouldNotGetDocument2() throws Exception {

        mockMvc.perform(
                get("/document/2"))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "admin")
    public void adminShouldEditDocument() throws Exception {

        Book d1=new Book( 1L,"Document 1 Edited");
        mockMvc.perform(
                put("/document/1")
                        .content(objectMapper.writeValueAsString(d1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(d1)));

    }

    @Test
    @WithMockUser(username = "user1")
    public void user1ShouldNotEditDocument() throws Exception {

        Book d1=new Book( 1L,"Document 1 Edited");
        mockMvc.perform(
                put("/document/1")
                        .content(objectMapper.writeValueAsString(d1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }


    @Test
    @WithMockUser(authorities = { "ROLE_ADMIN" })
    public void adminShouldSetPermissionAndPostDocument() throws Exception {

        Book d4=new Book(4L, "Document 4");
        mockMvc.perform(
                post("/document")
                .content(objectMapper.writeValueAsString(d4))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(d4)));

    }
}
