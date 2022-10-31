package ru.otus.homework.hw07.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.service.AuthorService;
import ru.otus.homework.hw07.service.print.PrintService;

@ShellComponent
public class AuthorCommands extends CommonShell {

    private final AuthorService authorService;

    private final PrintService<Author> printService;

    protected AuthorCommands(AuthorService authorService, PrintService<Author> printService) {
        this.authorService = authorService;
        this.printService = printService;
    }

    @ShellMethod(value = "Output all authors", key = "print-authors")
    public String outputAllAuthors() {
        return printService.objectsToPrint(authorService.getAll());
    }

    @ShellMethod(value = "Add an author. Accepts full name", key = "add-author")
    public String addAuthor(@ShellOption String fullName) {
        return String.format("Author added. ID: %d", authorService.add(fullName).getId());
    }

    @ShellMethod(value = "Delete a author by ID", key = "delete-author-id")
    public void deleteAuthorById(@ShellOption long authorId) {
        authorService.delete(authorId);
    }

}
