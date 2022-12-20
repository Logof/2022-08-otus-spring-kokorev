package ru.otus.homework.hw08.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw08.entity.Genre;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenrePrintService implements PrintService<Genre> {
    @Override
    public String objectsToPrint(List<Genre> objects) {
        return String.format("Total genres: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(Genre::getGenreName))
                        .map(genre -> String.format("%s", genre.getGenreName()))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(Genre object) {
        return String.format("\t%s", object.getGenreName());
    }
}
