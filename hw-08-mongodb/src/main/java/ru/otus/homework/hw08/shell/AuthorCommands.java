package ru.otus.homework.hw08.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.service.AuthorService;
import ru.otus.homework.hw08.service.print.PrintService;

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
        return String.format("Author %s added.", authorService.add(fullName).getFullName());
    }

    @ShellMethod(value = "Delete a author by full name", key = "delete-author-by-full-name")
    public void deleteAuthorById(@ShellOption String fullName) {
        authorService.delete(fullName);
    }

}