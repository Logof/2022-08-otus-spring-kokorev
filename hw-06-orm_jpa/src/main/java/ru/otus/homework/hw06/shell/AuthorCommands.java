package ru.otus.homework.hw06.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw06.service.AuthorService;

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
        authorService.add(fullName);
    }

    @ShellMethod(value = "Delete a author by ID", key = "delete-author-id")
    public void deleteAuthorById(@ShellOption long authorId) {
        authorService.delete(authorId);
    }

}
