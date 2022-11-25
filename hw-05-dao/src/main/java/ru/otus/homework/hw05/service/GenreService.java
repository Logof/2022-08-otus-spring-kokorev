package ru.otus.homework.hw05.service;

import ru.otus.homework.hw05.entity.Genre;

public interface GenreService {
    void delete(long genreId);

    Genre add(String genreName);

    void outputAll();
}
