package ru.otr.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.service.impl.GenrePrintService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущьности \"Жанр\" к печати")
public class GenrePrintServiceTest {
    private GenrePrintService genrePrintService = new GenrePrintService();

    @DisplayName("Подготовка к печати списка авторов")
    @Test
    public void verificationPreparingPrintAuthorsList() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre(1, "Genre 1"));
        genreList.add(new Genre(2, "Genre 2"));
        genreList.add(new Genre(3, "Genre 3"));

        String stringExpect = genrePrintService.objectsToPrint(genreList);
        String stringActual = "\tGenre 1 (id=1)" + System.lineSeparator() + "\tGenre 2 (id=2)" + System.lineSeparator() + "\tGenre 3 (id=3)";
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати автора")
    @Test
    public void verificationPreparingPrintAuthor() {
        Genre genre = new Genre(1, "Genre 1");
        String stringExpect = genrePrintService.objectToPrint(genre);
        String stringActual = "\tGenre 1 (id=1)";
        assertEquals(stringExpect, stringActual);
    }
}
