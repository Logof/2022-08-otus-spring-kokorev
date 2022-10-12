package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Comment;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.service.PrintService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookPrintService implements PrintService<Book> {
    private final PrintService<Genre> genrePrintService;

    private final PrintService<Author> authorPrintService;

    private final PrintService<Comment> commentPrintService;

    public BookPrintService(PrintService<Genre> genrePrintService,
                            PrintService<Author> authorPrintService,
                            PrintService<Comment> commentPrintService) {
        this.genrePrintService = genrePrintService;
        this.authorPrintService = authorPrintService;
        this.commentPrintService = commentPrintService;
    }


    @Override
    public String objectsToPrint(List<Book> objects) {
        return String.format("Total books: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().map(book -> objectToPrint(book))
                        .collect(Collectors.joining(System.lineSeparator(), "", "")));
    }

    @Override
    public String objectToPrint(Book object) {

        String genresPrintString = "";
        String authorsPrintString = "";
        String commentsPrintString = "";

        if (object.getGenres() != null && object.getGenres().size() > 0) {
            genresPrintString = genrePrintService.objectsToPrint(object.getGenres());
        }

        if (object.getAuthors() != null && object.getAuthors().size() > 0) {
            authorsPrintString = authorPrintService.objectsToPrint(object.getAuthors());
        }

        if (object.getComments() != null && object.getComments().size() > 0) {
            commentsPrintString = commentPrintService.objectsToPrint(object.getComments());
        }


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Title: %s (ISBN: %s)", object.getTitle(), object.getIsbn()))
                .append(System.lineSeparator())
                .append("Genre: ").append(System.lineSeparator()).append(genresPrintString)
                .append(System.lineSeparator())
                .append("Authors: ").append(System.lineSeparator()).append(authorsPrintString)
                .append(System.lineSeparator())
                .append("Comments: ").append(System.lineSeparator()).append(commentsPrintString)
                .append(System.lineSeparator())
                .append("---------------------------------------")
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
