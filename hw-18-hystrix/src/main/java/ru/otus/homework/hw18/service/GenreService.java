package ru.otus.homework.hw18.service;

import ru.otus.homework.hw18.dto.GenreDto;

import java.util.List;

public interface GenreService {

    void delete(Long genreId);

    GenreDto add(String genreName);

    List<GenreDto> getAll();
}
