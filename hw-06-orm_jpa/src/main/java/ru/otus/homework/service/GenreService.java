package ru.otus.homework.service;

public interface GenreService {
    void delete(long genreId);

    void add(String genreName);

    void outputAll();
}
