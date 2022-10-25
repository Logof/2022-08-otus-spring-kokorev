package ru.otus.homework.service;

import ru.otus.homework.entity.Genre;

public interface GenreService {
    void delete(long genreId);

    Genre add(String genreName);

    void outputAll();
}
