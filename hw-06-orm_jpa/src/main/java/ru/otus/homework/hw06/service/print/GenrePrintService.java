package ru.otus.homework.hw06.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw06.entity.Genre;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenrePrintService implements PrintService<Genre> {
    @Override
    public String objectsToPrint(Set<Genre> objects) {
        return String.format("Total genres: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(Genre::getGenreName))
                        .map(genre -> String.format("%s (id=%d)", genre.getGenreName(), genre.getId()))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(Genre object) {
        return String.format("\t%s (id=%d)", object.getGenreName(), object.getId());
    }
}
