package ru.otus.homework.hw06.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw06.service.GenreService;

@ShellComponent
public class GenreCommands extends CommonShell {

    private final GenreService genreService;

    public GenreCommands(GenreService genreService) {
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
