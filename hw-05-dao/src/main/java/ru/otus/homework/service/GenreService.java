package ru.otus.homework.service;

import ru.otus.homework.entity.Genre;

public interface GenreService {
    void delete(long genreId);

    void add(String genreName);

    Genre findByGenreName(String genreName);

    void outputAll();

}
