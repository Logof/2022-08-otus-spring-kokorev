package ru.otus.homework.hw13.service;

import ru.otus.homework.hw13.dto.GenreDto;

import java.util.List;

public interface GenreService {

    void delete(long genreId);

    GenreDto add(String genreName);

    List<GenreDto> getAll();
}
