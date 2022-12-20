package ru.otus.homework.hw08.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Genre;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookPrintService implements PrintService<Book> {

    private final PrintService<Genre> printServiceGenre;

    private final PrintService<Author> printServiceAuthor;

    public BookPrintService(PrintService<Genre> printServiceGenre,
                            PrintService<Author> printServiceAuthor) {
        this.printServiceGenre = printServiceGenre;
        this.printServiceAuthor = printServiceAuthor;
    }

    @Override
    public String objectsToPrint(List<Book> objects) {
        return String.format("Total books: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList())
                        .stream().map(book -> objectToPrint(book))
                        .collect(Collectors.joining(System.lineSeparator(), "", "")));
    }

    @Override
    public String objectToPrint(Book object) {

        String genresPrintString = "";
        String authorsPrintString = "";

        if (object.getGenres() != null && object.getGenres().size() > 0) {
            genresPrintString = printServiceGenre.objectsToPrint(object.getGenres());
        }

        if (object.getAuthors() != null && object.getAuthors().size() > 0) {
            authorsPrintString = printServiceAuthor.objectsToPrint(object.getAuthors());
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Title: %s (ISBN: %s)", object.getTitle(), object.getIsbn()))
                .append(System.lineSeparator())
                .append("Genre(s): ").append(System.lineSeparator()).append(genresPrintString)
                .append(System.lineSeparator())
                .append("Author(s): ").append(System.lineSeparator()).append(authorsPrintString)
                .append(System.lineSeparator())
                .append("---------------------------------------")
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
