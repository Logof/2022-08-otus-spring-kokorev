package ru.otus.homework.hw08.service;

import ru.otus.homework.hw08.entity.Genre;

import java.util.List;

public interface GenreService {
    void delete(String genreName);

    Genre add(String genreName);

    List<Genre> getAll();
}
