package ru.otus.homework.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class WorkWithLinksCommands {

    private final CommonShell commonShell;

    public WorkWithLinksCommands(CommonShell commonShell) {
        this.commonShell = commonShell;
    }

    @ShellMethod(value = "Add an author to a book. Accepts author id", key = "add-link-author")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addAnAuthorToABook(@ShellOption long authorId) {
        commonShell.getEventsPublisher().addAnAuthorToABook(commonShell.getIsbn(), authorId);
    }

    @ShellMethod(value = "Add an genre to a book. Accepts genre id", key = "add-link-genre")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addAnGenreToABook(@ShellOption long genreId) {
        commonShell.getEventsPublisher().addAnGenreToABook(commonShell.getIsbn(), genreId);
    }

    @ShellMethod(value = "Remove the author from the book. Accepts author id", key = "remove-link-author")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void removeTheAuthorFromTheBook(@ShellOption long authorId) {
        commonShell.getEventsPublisher().removeTheAuthorFromTheBook(commonShell.getIsbn(), authorId);
    }

    @ShellMethod(value = "Remove the genre from the book. Accepts genre id", key = "remove-link-genre")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void removeTheGenreFromTheBook(@ShellOption long genreId) {
        commonShell.getEventsPublisher().removeTheGenreFromTheBook(commonShell.getIsbn(), genreId);
    }


}
