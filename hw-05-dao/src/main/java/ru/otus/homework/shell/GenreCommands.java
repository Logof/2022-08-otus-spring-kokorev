package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.GenreService;

@ShellComponent
public class GenreCommands {

    //TODO сделать метод по добавлению жанра к книге
    private final CommonShell commonShell;

    private final GenreService genreService;

    public GenreCommands(CommonShell commonShell, GenreService genreService) {
        this.commonShell = commonShell;
        this.genreService = genreService;
    }

    @ShellMethod(value = "Output all genres", key = "print-genres")
    public void outputAllGenres() {
        genreService.outputAll();
    }

    @ShellMethod(value = "Add an genre. Accepts genre name", key = "add-genre")
    public void addGenre(@ShellOption String genreName) {
        genreService.add(genreName);
    }

    @ShellMethod(value = "Delete a genre by ID", key = "delete-genre")
    public void deleteGenre(@ShellOption long genreId) {
        genreService.delete(genreId);
    }

}
