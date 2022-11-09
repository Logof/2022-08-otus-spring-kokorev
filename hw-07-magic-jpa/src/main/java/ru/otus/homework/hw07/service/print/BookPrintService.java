package ru.otus.homework.hw07.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw07.entity.dto.AuthorDto;
import ru.otus.homework.hw07.entity.dto.BookDto;
import ru.otus.homework.hw07.entity.dto.GenreDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookPrintService implements PrintService<BookDto> {

    private final PrintService<GenreDto> genrePrintService;

    private final PrintService<AuthorDto> authorPrintService;

    public BookPrintService(PrintService<GenreDto> genrePrintService,
                            PrintService<AuthorDto> authorPrintService) {
        this.genrePrintService = genrePrintService;
        this.authorPrintService = authorPrintService;
    }

    @Override
    public String objectsToPrint(List<BookDto> objects) {
        return String.format("Total books: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(BookDto::getTitle)).collect(Collectors.toList())
                        .stream().map(book -> objectToPrint(book))
                        .collect(Collectors.joining(System.lineSeparator(), "", "")));
    }

    @Override
    public String objectToPrint(BookDto object) {

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
