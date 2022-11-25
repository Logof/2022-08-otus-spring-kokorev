package ru.otus.homework.hw06.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw06.entity.Author;
import ru.otus.homework.hw06.entity.Book;
import ru.otus.homework.hw06.entity.Genre;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookPrintService implements PrintService<Book> {

    private final PrintService<Genre> genrePrintService;

    private final PrintService<Author> authorPrintService;

    public BookPrintService(PrintService<Genre> genrePrintService,
                            PrintService<Author> authorPrintService) {
        this.genrePrintService = genrePrintService;
        this.authorPrintService = authorPrintService;
    }


    @Override
    public String objectsToPrint(Set<Book> objects) {
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
            genresPrintString = genrePrintService.objectsToPrint(object.getGenres());
        }

        if (object.getAuthors() != null && object.getAuthors().size() > 0) {
            authorsPrintString = authorPrintService.objectsToPrint(object.getAuthors());
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Title: %s (ISBN: %s)", object.getTitle(), object.getIsbn()))
                .append(System.lineSeparator())
                .append("Genre: ").append(System.lineSeparator()).append(genresPrintString)
                .append(System.lineSeparator())
                .append("Authors: ").append(System.lineSeparator()).append(authorsPrintString)
                .append(System.lineSeparator())
                .append("---------------------------------------")
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
