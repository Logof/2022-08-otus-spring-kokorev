package ru.otus.homework.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookCommands {

    private final CommonShell commonShell;

    public BookCommands(CommonShell commonShell) {
        this.commonShell = commonShell;
    }

    @ShellMethod(value = "Select a book by ISBN", key = "select-book")
    public void selectABook(@ShellOption String isbn) {
        commonShell.setIsbn(isbn);
    }

    @ShellMethod(value = "Output all books", key = "output-books")
    public void outputAllBooks() {
        commonShell.getEventsPublisher().outputAllBooks();
    }

    @ShellMethod(value = "Output the selected book", key = "output-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputTheSelectedBook() {
        commonShell.getEventsPublisher().outputBook(commonShell.getIsbn());
    }

    @ShellMethod(value = "Add a book. Accepts the name of the book", key = "add-book")
    public void addBook(@ShellOption String isbn, @ShellOption String title) {
        commonShell.getEventsPublisher().addBook(isbn, title);
    }

    @ShellMethod(value = "Delete selected book by ISBN", key = "delete-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void deleteBookById() {
        commonShell.getEventsPublisher().deleteBookById(commonShell.getIsbn());
        commonShell.setIsbn(null);
    }

}
