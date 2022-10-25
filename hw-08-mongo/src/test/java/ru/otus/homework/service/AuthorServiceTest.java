package ru.otus.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.entity.Author;
import ru.otus.homework.service.impl.OutputServiceStreams;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест сервиса по работе с авторами")
@DataMongoTest
@ComponentScan("ru.otus.homework")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorServiceTest {

    @MockBean
    private OutputServiceStreams outputServiceStreams;

    @Autowired
    private AuthorService authorService;

    @Test
    void findOrCreateTest() {
        Author authorFind = authorService.save(new Author("Author Find", new ArrayList<>()));
        Author authorFindExpect = authorService.findOrCreate("Author Find");
        assertEquals(authorFindExpect, authorFind);

        Author authorCreateExpect = authorService.findOrCreate("Author Save");
        assertNotNull(authorCreateExpect);
        assertEquals(authorCreateExpect.getFullName(), "Author Save");
        assertEquals(authorCreateExpect.getBookIsbns(), new ArrayList<>());
    }

    @Test
    void saveTest() {
        Author author = new Author("Автор");
        Author authorExpect = authorService.save(author);

        Assertions.assertEquals(authorExpect.getFullName(), author.getFullName());
        assertEquals(authorExpect.getBookIsbns(), new ArrayList<>());
    }

    @Test
    void deleteTest() {
        Author authorForDelete = authorService.save(new Author("Author Find", new ArrayList<>()));
        assertNotNull(authorService.findOrCreate(authorForDelete.getFullName()));
        authorService.delete(authorForDelete.getFullName());

        Author authorForDeleteExpect = authorService.findOrCreate(authorForDelete.getFullName());
        assertNull(authorForDeleteExpect);
    }

    @Test
    void outputAllTest() {

    }
}
