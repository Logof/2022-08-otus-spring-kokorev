package ru.otus.homework.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookPrintService implements PrintService<Book> {


    @Override
    public String objectsToPrint(List<Book> objects) {
        return String.format("Total books: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(Book::getIsbn)).collect(Collectors.toList())
                        .stream().map(book -> objectToPrint(book))
                        .collect(Collectors.joining(System.lineSeparator(), "", "")));
    }

    private String stringsToPrint(List<String> stringList) {
        return String.format("Total: %d%s%s", stringList.size(), System.lineSeparator(),
                stringList.stream().sorted().collect(Collectors.toList())
                        .stream().map(string -> String.format("\t%s", string))
                        .collect(Collectors.joining(System.lineSeparator(), "", "")));
    }

    @Override
    public String objectToPrint(Book object) {

        String genresPrintString = "";
        String authorsPrintString = "";

        if (object.getGenres() != null && object.getGenres().size() > 0) {
            genresPrintString = stringsToPrint(object.getGenres());
        }

        if (object.getAuthors() != null && object.getAuthors().size() > 0) {
            authorsPrintString = stringsToPrint(object.getAuthors());
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
