package ru.otus.homework.hw06.service;

public interface GenreService {
    void delete(long genreId);

    void add(String genreName);

    void outputAll();
}
