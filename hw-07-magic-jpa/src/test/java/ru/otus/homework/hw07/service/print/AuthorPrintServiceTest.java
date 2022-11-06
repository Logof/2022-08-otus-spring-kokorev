package ru.otus.homework.hw07.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw07.entity.dto.AuthorDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Автор\" к печати")
public class AuthorPrintServiceTest {

    private final AuthorPrintService authorPrintService = new AuthorPrintService();

    @DisplayName("Подготовка к печати списка авторов")
    @Test
    public void verificationPreparingPrintAuthorsList() {
        List<AuthorDto> authorList = new ArrayList<>();
        authorList.add(new AuthorDto(1L, "Author 1"));
        authorList.add(new AuthorDto(2L, "Author 2"));
        authorList.add(new AuthorDto(3L, "Author 3"));

        String stringExpect = authorPrintService.objectsToPrint(authorList);
        String stringActual = "Total authors: 3" + System.lineSeparator() + "\tAuthor 1 (id=1)" + System.lineSeparator() + "\tAuthor 2 (id=2)" + System.lineSeparator() + "\tAuthor 3 (id=3)";
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати автора")
    @Test
    public void verificationPreparingPrintAuthor() {
        AuthorDto author = new AuthorDto(1L, "Author 1");
        String stringExpect = authorPrintService.objectToPrint(author);
        String stringActual = "\tAuthor 1 (id=1)";
        assertEquals(stringExpect, stringActual);
    }

}
