package ru.otus.homework.hw07.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw07.entity.dto.AuthorDto;
import ru.otus.homework.hw07.entity.dto.BookDto;
import ru.otus.homework.hw07.entity.dto.GenreDto;
import ru.otus.homework.hw07.service.BookService;
import ru.otus.homework.hw07.service.print.PrintService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class BookCommands extends CommonShell {

    private final PrintService<BookDto> printService;

    private final BookService bookService;

    public BookCommands(PrintService<BookDto> printService, BookService bookService) {
        this.printService = printService;
        this.bookService = bookService;
    }

    @ShellMethod(value = "Select a book by ISBN", key = "select-book")
    public void selectABook(@ShellOption String isbn) {
        setCurrBook(isbn);
    }

    @ShellMethod(value = "Output all books", key = "print-books")
    public String printAllBooks() {
        return printService.objectsToPrint(bookService.getAll());
    }

    @ShellMethod(value = "Output all books", key = "print-books-by-author")
    public String printAllBooksByAuthors(@ShellOption(help = "Author full name ") String authorFullName) {
        return printService.objectsToPrint(bookService.getAllByAuthor(authorFullName));
    }

    @ShellMethod(value = "Output all books", key = "print-books-by-genre")
    public String printAllBooksByGenre(@ShellOption(help = "Genre name ") String genreName) {
        return printService.objectsToPrint(bookService.getAllByGenre(genreName));
    }

    @ShellMethod(value = "Output the selected book", key = "print-book")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public String printSelectedBook() {
        return printService.objectToPrint(bookService.getByIsbn(getCurrBook()));
    }

    @ShellMethod(value = "Add a book. Usage ISBN TITLE [AUTHORS] [GENRES]", key = "add-book")
    public void addBook(@ShellOption(help = "ISBN") String isbn,
                          @ShellOption(help = "Book title") String title,
                          @ShellOption(help = "Authors(s)", defaultValue = "") String[] authors,
                          @ShellOption(help = "Genre(s)", defaultValue = "") String[] genres) {
        List<AuthorDto> authorList = Arrays.asList(authors).stream().map(authorFullName ->
                new AuthorDto(authorFullName)).collect(Collectors.toList());
        List<GenreDto> genreList = Arrays.asList(genres).stream().map(genreName ->
                new GenreDto(genreName)).collect(Collectors.toList());
        bookService.add(new BookDto(isbn, title, authorList, genreList));
    }


    @ShellMethod(value = "Update the title of the selected book by ISBN", key = "update-book-title")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void updateTitleBookById(@ShellOption String newTitle) {
        bookService.updateTitle(getCurrBook(), newTitle);
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
