package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.AuthorService;

@ShellComponent
public class AuthorCommands extends CommonShell {

    private final AuthorService authorService;

    protected AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "Output all authors", key = "print-authors")
    public void outputAllAuthors() {
        authorService.outputAll();
    }

    @ShellMethod(value = "Add an author. Accepts full name", key = "add-author")
    public void addAuthor(@ShellOption String fullName) {
        authorService.findOrCreate(fullName);
    }

    @ShellMethod(value = "Delete a author by ID", key = "delete-author")
    public void deleteAuthorById(@ShellOption String fullName) {
        authorService.delete(fullName);
    }

}