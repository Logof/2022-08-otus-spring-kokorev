package ru.otus.homework.hw09.service;

import ru.otus.homework.hw09.entity.dto.GenreDto;

import java.util.List;

public interface GenreService {
    void delete(long genreId);

    GenreDto add(String genreName);

    List<GenreDto> getAll();
}
