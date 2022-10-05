package ru.otus.homework.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorCommands {

    private final CommonShell commonShell;

    protected AuthorCommands(CommonShell commonShell) {
        this.commonShell = commonShell;
    }

    @ShellMethod(value = "Output all authors", key = "output-authors")
    public void outputAllAuthors() {
        commonShell.getEventsPublisher().outputAllAuthors();
    }

    @ShellMethod(value = "Add an author. Accepts full name", key = "add-author")
    public void addAuthor(@ShellOption String fullName) {
        commonShell.getEventsPublisher().addAuthor(fullName);
    }

    @ShellMethod(value = "Delete a author by ID", key = "delete-author-id")
    public void deleteAuthorById(@ShellOption long authorId) {
        commonShell.getEventsPublisher().deleteAuthorById(authorId);
    }
}
