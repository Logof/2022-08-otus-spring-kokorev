package ru.otus.homework.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.entity.Book;

@ShellComponent
public class BookCommands {

    private final CommonShell commonShell;

    public BookCommands(CommonShell commonShell) {
        this.commonShell = commonShell;
    }

    @ShellMethod(value = "Select a book by ISBN", key = "select-book")
    public void selectABook(@ShellOption String isbn) {
        commonShell.setCurrBook(isbn);
    }

    @ShellMethod(value = "Output all books", key = "print-books")
    public void outputAllBooks() {
        commonShell.getEventsPublisher().outputAllBooks();
    }

    @ShellMethod(value = "Output the selected book", key = "print-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputTheSelectedBook() {
        commonShell.getEventsPublisher().outputBook(commonShell.getCurrBook());
    }

    @ShellMethod(value = "Add a book. Accepts the name of the book", key = "add-book")
    public void addBook(@ShellOption String isbn, @ShellOption String title) {
        commonShell.getEventsPublisher().addBook(isbn, title);
    }

    @ShellMethod(value = "Update selected book by ISBN", key = "update-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void updateFullBookById(@ShellOption String newIsbn, @ShellOption(defaultValue = "") String newTitle) {
        commonShell.getEventsPublisher().updateBook(commonShell.getCurrBook(), new Book(newIsbn, newTitle));
        commonShell.setCurrBook(newIsbn);
    }

    @ShellMethod(value = "Delete selected book by ISBN", key = "delete-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void deleteBookById() {
        commonShell.getEventsPublisher().deleteBookById(commonShell.getCurrBook());
        commonShell.setCurrBook(null);
    }

}
