package ru.otus.homework.hw12.security;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.homework.hw12.entity.dto.BookDto;
import ru.otus.homework.hw12.service.BookService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserAccountDataControllerTest {

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithAnonymousUser
    public void unauthorizedUserAccessTest() throws Exception
    {
        when(bookService.getByIsbn(anyString())).thenReturn(new BookDto());

        List<String> urlTemplateGetList = Arrays.asList("/book?isbn=123", "/edit?isbn=123", "/new", "/delete");
        List<String> urlTemplatePostList = Arrays.asList("/book", "/delete");

        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.ALL))
                .andExpect(status().isOk());

        for (String urlTemplate : urlTemplateGetList) {
            mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate).accept(MediaType.ALL))
                    .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
        }

        for (String urlTemplate : urlTemplatePostList) {
            mockMvc.perform(MockMvcRequestBuilders.post(urlTemplate).accept(MediaType.ALL))
                    .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
        }
    }

    @Test
    public void authorizedRoleAccessTest() throws Exception
    {
        when(bookService.getByIsbn(anyString())).thenReturn(new BookDto());

        Map<String, ResultMatcher> adminGetResults = new HashMap<>();
        adminGetResults.put("/", status().isOk());
        adminGetResults.put("/book?isbn=123", status().isOk());
        adminGetResults.put("/edit?isbn=123", status().isOk());
        adminGetResults.put("/new", status().isOk());
        adminGetResults.put("/delete", status().isOk());

        Map<String, ResultMatcher> userGetResults = new HashMap<>();
        userGetResults.put("/", status().isOk());
        userGetResults.put("/book?isbn=123", status().isOk());
        userGetResults.put("/edit?isbn=123", status().is4xxClientError());
        userGetResults.put("/new", status().is4xxClientError());
        userGetResults.put("/delete", status().is4xxClientError());


        List<String> roleList = Arrays.asList("USER", "ADMIN");

        for (String role : roleList) {
            for (Map.Entry<String, ResultMatcher> entry :
                    (role.equals("USER") ? userGetResults : adminGetResults).entrySet()
            ) {
                mockMvc.perform(get(entry.getKey())
                                .with(user("user")
                                        .password("123456")
                                        .roles(role)))
                        .andExpect(entry.getValue());
            }
        }

        Map<String, ResultMatcher> adminPostResults = new HashMap<>();
        adminPostResults.put("/book", status().is3xxRedirection());
        adminPostResults.put("/delete", status().is3xxRedirection());

        Map<String, ResultMatcher> userPostResults = new HashMap<>();
        userPostResults.put("/book", status().is4xxClientError());
        userPostResults.put("/delete", status().is4xxClientError());

        for (String role : roleList) {
            for (Map.Entry<String, ResultMatcher> entry :
                    (role.equals("USER") ? userPostResults : adminPostResults).entrySet()
            ) {
                mockMvc.perform(post(entry.getKey())
                                .with(user("user")
                                        .password("123456")
                                        .roles(role)))
                        .andExpect(entry.getValue());
            }
        }
    }
}