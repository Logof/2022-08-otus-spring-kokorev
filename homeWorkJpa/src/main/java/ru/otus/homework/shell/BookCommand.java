package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.service.BookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ShellComponent
public class BookCommand {
    private final BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Output all books", key = "print-books")
    public void outputAllBooks() {
        bookService.getAll();
    }

    @ShellMethod(value = "Add a book. Usage ISBN TITLE [--authors {AUTHORS}] [--genres {GENRES}]", key = "add-book")
    public void addBook(@ShellOption(help = "ISBN") String isbn,
                        @ShellOption(help = "Book title") String title,
                        @ShellOption(help = "Authors(s)",defaultValue = "") String[] authors,
                        @ShellOption(help = "Genre(s)", defaultValue = "") String[] genres) {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(null, "Author"));

        bookService.add(new Book(isbn, title, authorList));
    }
}
