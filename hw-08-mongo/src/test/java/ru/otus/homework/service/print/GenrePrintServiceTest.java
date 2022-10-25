package ru.otus.homework.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Жанр\" к печати")
public class GenrePrintServiceTest {
    private final GenrePrintService genrePrintService = new GenrePrintService();

    @DisplayName("Подготовка к печати списка жанров")
    @Test
    public void verificationPreparingPrintAuthorsList() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre("Genre 1", new ArrayList<>()));
        genreList.add(new Genre("Genre 2", new ArrayList<>()));
        genreList.add(new Genre("Genre 3", new ArrayList<>()));

        String stringExpect = genrePrintService.objectsToPrint(genreList);
        String stringActual = "Total genres: 3" + System.lineSeparator() + "\tGenre 1" + System.lineSeparator() + "\tGenre 2" + System.lineSeparator() + "\tGenre 3";
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати жанра")
    @Test
    public void verificationPreparingPrintAuthor() {
        Genre genre = new Genre("Genre 1", new ArrayList<>());
        String stringExpect = genrePrintService.objectToPrint(genre);
        String stringActual = "\tGenre 1";
        assertEquals(stringExpect, stringActual);
    }
}
