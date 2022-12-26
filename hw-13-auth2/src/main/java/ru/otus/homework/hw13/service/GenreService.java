package ru.otus.homework.hw13.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw13.dto.GenreDto;

import java.util.List;

public interface GenreService {
    @Transactional
    void delete(long genreId);

    @Transactional
    GenreDto add(String genreName);

    @Transactional(readOnly = true)
    List<GenreDto> getAll();
}
