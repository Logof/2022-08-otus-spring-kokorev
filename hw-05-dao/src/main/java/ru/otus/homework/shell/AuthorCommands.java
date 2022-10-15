package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.AuthorService;

@ShellComponent
public class AuthorCommands {

    //TODO сделать метод по добавлению автора к книге
    private final CommonShell commonShell;

    private final AuthorService authorService;

    protected AuthorCommands(CommonShell commonShell, AuthorService authorService) {
        this.commonShell = commonShell;
        this.authorService = authorService;
    }

    @ShellMethod(value = "Output all authors", key = "print-authors")
    public void outputAllAuthors() {
        authorService.outputAll();
    }

    @ShellMethod(value = "Add an author. Accepts full name", key = "add-author")
    public void addAuthor(@ShellOption String fullName) {
        authorService.add(fullName);
    }

    @ShellMethod(value = "Delete a author by ID", key = "delete-author-id")
    public void deleteAuthorById(@ShellOption long authorId) {
        authorService.delete(authorId);
    }
}