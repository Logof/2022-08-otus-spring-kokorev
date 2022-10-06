package ru.otr.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Author;
import ru.otus.homework.service.impl.AuthorPrintService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущьности \"Автор\" к печати")
public class AuthorPrintServiceTest {

    private AuthorPrintService authorPrintService = new AuthorPrintService();

    @DisplayName("Подготовка к печати списка авторов")
    @Test
    public void verificationPreparingPrintAuthorsList() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1, "Author 1"));
        authorList.add(new Author(2, "Author 2"));
        authorList.add(new Author(3, "Author 3"));

        String stringExpect = authorPrintService.objectsToPrint(authorList);
        String stringActual = "\tAuthor 1 (id=1)" + System.lineSeparator() + "\tAuthor 2 (id=2)" + System.lineSeparator() + "\tAuthor 3 (id=3)";
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати автора")
    @Test
    public void verificationPreparingPrintAuthor() {
        Author author = new Author(1, "Author 1");
        String stringExpect = authorPrintService.objectToPrint(author);
        String stringActual = "\tAuthor 1 (id=1)";
        assertEquals(stringExpect, stringActual);
    }

}
