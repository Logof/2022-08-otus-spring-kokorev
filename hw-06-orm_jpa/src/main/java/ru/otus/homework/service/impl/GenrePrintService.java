package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.service.PrintService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenrePrintService implements PrintService<Genre> {
    @Override
    public String objectsToPrint(List<Genre> objects) {
        return String.format("Total genres: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().map(genre -> String.format("%s (id=%d)", genre.getGenreName(), genre.getId()))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(Optional<Genre> object) {
        return object.isPresent()
                ? String.format("\t%s (id=%d)", object.get().getGenreName(), object.get().getId())
                : "";
    }

}
