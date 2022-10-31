package ru.otus.homework.hw07.service;

import ru.otus.homework.hw07.entity.Genre;

import java.util.List;

public interface GenreService {
    void delete(long genreId);

    Genre add(String genreName);

    List<Genre> getAll();
}
