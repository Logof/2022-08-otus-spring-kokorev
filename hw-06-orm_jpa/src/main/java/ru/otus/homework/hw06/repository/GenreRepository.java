package ru.otus.homework.hw06.repository;

import ru.otus.homework.hw06.entity.Genre;

import java.util.Set;

public interface GenreRepository {

    void deleteById(long id);

    Set<Genre> getAll();

    Genre getGenreById(Long id);

    Genre getGenreByName(String genreName);

    boolean genreHasBooks(long id);

    Genre save(Genre object);
}
