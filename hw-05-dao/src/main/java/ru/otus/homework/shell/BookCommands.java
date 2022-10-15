package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.entity.Book;
import ru.otus.homework.service.BookService;

import java.util.Arrays;

@ShellComponent
public class BookCommands {

    private final CommonShell commonShell;

    private final BookService bookService;

    public BookCommands(CommonShell commonShell, BookService bookService) {
        this.commonShell = commonShell;
        this.bookService = bookService;
    }

    @ShellMethod(value = "Select a book by ISBN", key = "select-book")
    public void selectABook(@ShellOption String isbn) {
        commonShell.setCurrBook(isbn);
    }

    @ShellMethod(value = "Output all books", key = "print-books")
    public void outputAllBooks() {
        bookService.getAll();
    }

    @ShellMethod(value = "Output the selected book", key = "print-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputTheSelectedBook() {
        bookService.getById(commonShell.getCurrBook());
    }

    @ShellMethod(value = "Add a book. Usage ISBN TITLE [{AUTHORS}] [{GENRES}]", key = "add-book")
    public void addBook(@ShellOption(help = "ISBN") String isbn,
                        @ShellOption(help = "Book title") String title,
                        @ShellOption(help = "Authors(s)",defaultValue = "") String[] authors,
                        @ShellOption(help = "Genre(s)", defaultValue = "") String[] genres) {
        bookService.add(new Book(isbn, title), Arrays.asList(authors), Arrays.asList(genres));
    }

    @ShellMethod(value = "Update the title of the selected book by ISBN", key = "update-book-title")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void updateFullBookById(@ShellOption String newTitle) {
        bookService.updateTitle(new Book(commonShell.getCurrBook(), newTitle));
    }

    @ShellMethod(value = "Delete selected book by ISBN", key = "delete-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void deleteBookById() {
        bookService.deleteById(commonShell.getCurrBook());
        commonShell.setCurrBook(null);
    }

}
