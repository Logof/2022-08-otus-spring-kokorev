package ru.otus.homework.service;

import ru.otus.homework.entity.Genre;

public interface GenreService {
    void delete(String genreName);

    Genre findOrCreate(String genreName);

    Genre save(Genre genre);

    void outputAll();
}
