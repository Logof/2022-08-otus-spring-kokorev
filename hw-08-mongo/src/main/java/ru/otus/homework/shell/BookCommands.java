package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.entity.Book;
import ru.otus.homework.service.BookService;

import java.util.ArrayList;

@ShellComponent
public class BookCommands extends CommonShell {

    private final BookService bookService;

    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Select a book by ISBN", key = "select-book")
    public void selectABook(@ShellOption String isbn) {
        setCurrBook(isbn);
    }

    @ShellMethod(value = "Output all books", key = "print-books")
    public void outputAllBooks() {
        bookService.getAll();
    }

    @ShellMethod(value = "Output all books", key = "print-books-by-author")
    public void outputAllBooksByAuthors(@ShellOption(help = "Author full name ") String authorFullName) {
        bookService.getAllByAuthor(authorFullName);
    }

    @ShellMethod(value = "Output all books", key = "print-books-by-genre")
    public void outputAllBooksByGenre(@ShellOption(help = "Genre name ") String genreName) {
        bookService.getAllByGenre(genreName);
    }

    @ShellMethod(value = "Output the selected book", key = "print-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputTheSelectedBook() {
        bookService.getByIsbn(getCurrBook());
    }

    @ShellMethod(value = "Add a book. Usage ISBN TITLE [--authors {AUTHORS}] [--genres {GENRES}]", key = "add-book")
    public void addBook(@ShellOption(help = "ISBN") String isbn,
                        @ShellOption(help = "Book title") String title,
                        @ShellOption(help = "Authors(s)", defaultValue = "") String[] authors,
                        @ShellOption(help = "Genre(s)", defaultValue = "") String[] genres) {
        bookService.save(new Book(isbn, title, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }


    @ShellMethod(value = "Update the title of the selected book by ISBN", key = "update-book-title")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void updateFullBookById(@ShellOption String newTitle) {
        //TODO сломано
        bookService.save(new Book(getCurrBook(), newTitle));
    }

    @ShellMethod(value = "Delete selected book by ISBN", key = "delete-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void deleteBookByIsbn() {
        bookService.deleteByIsbn(getCurrBook());
        setCurrBook(null);
    }

    @ShellMethod(value = "Add an genre to selected book. Accepts genre name", key = "add-genre-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addGenreToBook(@ShellOption String genreName) {
        bookService.addGenreToBook(getCurrBook(), genreName);
    }

    @ShellMethod(value = "Add an author to selected book. Accepts full name", key = "add-author-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addAuthorToBook(@ShellOption String fullName) {
        bookService.addAuthorToBook(getCurrBook(), fullName);
    }

    @ShellMethod(value = "Add an author to selected book. Accepts full name", key = "add-comment")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addCommentToBook(@ShellOption String commentText) {
        bookService.addCommentToBook(getCurrBook(), commentText);
    }

}
