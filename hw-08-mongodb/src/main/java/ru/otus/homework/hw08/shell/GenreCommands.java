package ru.otus.homework.hw08.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw08.entity.Genre;
import ru.otus.homework.hw08.exception.DeleteDataException;
import ru.otus.homework.hw08.service.GenreService;
import ru.otus.homework.hw08.service.print.PrintService;

@ShellComponent
public class GenreCommands extends CommonShell {

    private final GenreService genreService;

    private final PrintService<Genre> printService;

    public GenreCommands(GenreService genreService, PrintService<Genre> printService) {
        this.genreService = genreService;
        this.printService = printService;
    }

    @ShellMethod(value = "Output all genres", key = "print-genres")
    public String outputAllGenres() {
        return printService.objectsToPrint(genreService.getAll());
    }

    @ShellMethod(value = "Add an genre. Accepts genre name", key = "add-genre")
    public String addGenre(@ShellOption String genreName) {
        return String.format("Genre %s added", genreService.add(genreName).getGenreName());
    }

    @ShellMethod(value = "Delete a genre by name", key = "delete-genre-by-name")
    public String deleteGenre(@ShellOption String genreName) {
        try {
            genreService.delete(genreName);
            return "genre deleted successfully";
        } catch (DeleteDataException ex) {
            return ex.getMessage();
        }
    }

}