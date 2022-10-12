package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Author;
import ru.otus.homework.service.PrintService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorPrintService implements PrintService<Author> {
    @Override
    public String objectsToPrint(List<Author> objects) {
        return String.format("Total authors: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().map(author -> String.format("%s (id=%d)", author.getFullName(), author.getId()))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(Optional<Author> object) {
        return object.isPresent()
                ? String.format("\t%s (id=%d)", object.get().getFullName(), object.get().getId())
                : "";
    }
}
