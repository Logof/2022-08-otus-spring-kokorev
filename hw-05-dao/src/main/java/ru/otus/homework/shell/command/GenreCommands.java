package ru.otus.homework.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreCommands {

    private final CommonShell commonShell;

    public GenreCommands(CommonShell commonShell) {
        this.commonShell = commonShell;
    }

    @ShellMethod(value = "Output all genres", key = "output-genres")
    public void outputAllGenres() {
        commonShell.getEventsPublisher().outputAllGenres();
    }

    @ShellMethod(value = "Add an genre. Accepts genre name", key = "add-genre")
    public void addGenre(@ShellOption String genreName) {
        commonShell.getEventsPublisher().addGenre(genreName);
    }

    @ShellMethod(value = "Delete a genre by ID", key = "delete-genre")
    public void deleteGenre(@ShellOption long genreId) {
        commonShell.getEventsPublisher().deleteGenreById(genreId);
    }
}
