package ru.otus.homework.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Author;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Автор\" к печати")
public class AuthorPrintServiceTest {

    private final AuthorPrintService authorPrintService = new AuthorPrintService();

    @DisplayName("Подготовка к печати списка авторов")
    @Test
    public void verificationPreparingPrintAuthorsList() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author("Author 1", new ArrayList<>()));
        authorList.add(new Author("Author 2", new ArrayList<>()));
        authorList.add(new Author("Author 3", new ArrayList<>()));

        String stringExpect = authorPrintService.objectsToPrint(authorList);
        String stringActual = "Total authors: 3" + System.lineSeparator() + "\tAuthor 1" + System.lineSeparator() + "\tAuthor 2" + System.lineSeparator() + "\tAuthor 3";
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати автора")
    @Test
    public void verificationPreparingPrintAuthor() {
        Author author = new Author("Author 1", new ArrayList<>());
        String stringExpect = authorPrintService.objectToPrint(author);
        String stringActual = "\tAuthor 1";
        assertEquals(stringExpect, stringActual);
    }

}
