package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.entity.Book;
import ru.otus.homework.service.BookService;

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

    @ShellMethod(value = "Add a book. Accepts the name of the book", key = "add-book")
    public void addBook(@ShellOption String isbn, @ShellOption String title) {
        bookService.add(new Book(isbn, title));
    }

    @ShellMethod(value = "Update selected book by ISBN", key = "update-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void updateFullBookById(@ShellOption String newTitle) {
        bookService.update(new Book(commonShell.getCurrBook(), newTitle));
    }

    @ShellMethod(value = "Delete selected book by ISBN", key = "delete-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void deleteBookById() {
        bookService.deleteById(commonShell.getCurrBook());
        commonShell.setCurrBook(null);
    }

}
