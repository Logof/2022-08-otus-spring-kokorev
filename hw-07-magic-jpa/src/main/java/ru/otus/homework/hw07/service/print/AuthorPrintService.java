package ru.otus.homework.hw07.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw07.entity.dto.AuthorDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorPrintService implements PrintService<AuthorDto> {
    @Override
    public String objectsToPrint(List<AuthorDto> objects) {
        return String.format("Total authors: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(AuthorDto::getFullName))
                        .map(author -> String.format("%s (id=%d)", author.getFullName(), author.getId()))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(AuthorDto object) {
        return String.format("\t%s (id=%d)", object.getFullName(), object.getId());
    }
}
