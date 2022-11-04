package ru.otus.homework.hw05.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw05.entity.Author;
import ru.otus.homework.hw05.entity.Book;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @ShellMethodAvailability("isEmptyIsbn")
    public void outputTheSelectedBook() {
        bookService.getById(getCurrBook());
    }

    @ShellMethod(value = "Add a book. Usage ISBN TITLE [--authors {AUTHORS}] [--genres {GENRES}]", key = "add-book")
    public void addBook(@ShellOption(help = "ISBN") String isbn,
                        @ShellOption(help = "Book title") String title,
                        @ShellOption(help = "Authors(s)",defaultValue = "") String[] authors,
                        @ShellOption(help = "Genre(s)", defaultValue = "") String[] genres) {

        List<Author> authorList = Arrays.asList(authors).stream().map(authorFullName ->
                new Author(authorFullName)).collect(Collectors.toList());
        List<Genre> genreList = Arrays.asList(genres).stream().map(genreName ->
                new Genre(genreName)).collect(Collectors.toList());
        bookService.add(new Book(isbn, title, authorList, genreList));
    }


    @ShellMethod(value = "Update the title of the selected book by ISBN", key = "update-book-title")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void updateFullBookById(@ShellOption String newTitle) {
        bookService.updateTitle(new Book(getCurrBook(), newTitle));
    }

    @ShellMethod(value = "Delete selected book by ISBN", key = "delete-book")
    @ShellMethodAvailability("isEmptyIsbn")
    public void deleteBookById() {
        bookService.deleteById(getCurrBook());
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

}
