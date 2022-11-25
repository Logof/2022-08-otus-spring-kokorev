package ru.otus.homework.hw05.repository;

import ru.otus.homework.hw05.entity.Genre;

import java.util.List;

public interface GenreRepository {

    void deleteById(long id); 

    List<Genre> getAll(); 

    Genre getGenreById(Long id);

    Genre getGenreByName(String genreName);

    boolean genreHasBooks(long id);

    Genre insert(Genre object);

    int update(Genre object);
}
