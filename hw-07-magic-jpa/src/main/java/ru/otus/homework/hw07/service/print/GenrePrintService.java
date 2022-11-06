package ru.otus.homework.hw07.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw07.entity.dto.GenreDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenrePrintService implements PrintService<GenreDto> {
    @Override
    public String objectsToPrint(List<GenreDto> objects) {
        return String.format("Total genres: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(GenreDto::getGenreName))
                        .map(genre -> String.format("%s (id=%d)", genre.getGenreName(), genre.getId()))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(GenreDto object) {
        return String.format("\t%s (id=%d)", object.getGenreName(), object.getId());
    }
}
