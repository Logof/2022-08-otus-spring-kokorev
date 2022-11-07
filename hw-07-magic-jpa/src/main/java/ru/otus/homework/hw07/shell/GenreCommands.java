package ru.otus.homework.hw07.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw07.entity.dto.GenreDto;
import ru.otus.homework.hw07.service.GenreService;
import ru.otus.homework.hw07.service.print.PrintService;

@ShellComponent
public class GenreCommands extends CommonShell {

    private final GenreService genreService;

    private final PrintService<GenreDto> printService;

    public GenreCommands(GenreService genreService, PrintService<GenreDto> printService) {
        this.genreService = genreService;
        this.printService = printService;
    }

    @ShellMethod(value = "Output all genres", key = "print-genres")
    public String outputAllGenres() {
        return printService.objectsToPrint(genreService.getAll());
    }

    @ShellMethod(value = "Add an genre. Accepts genre name", key = "add-genre")
    public String addGenre(@ShellOption String genreName) {
        return String.format("Genre added. ID: %d", genreService.add(genreName).getId());
    }

    @ShellMethod(value = "Delete a genre by ID", key = "delete-genre-id")
    public void deleteGenre(@ShellOption long genreId) {
        genreService.delete(genreId);
    }

}
