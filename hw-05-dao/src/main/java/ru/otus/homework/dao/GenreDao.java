package ru.otus.homework.dao;

import ru.otus.homework.entity.Genre;

import java.util.List;

public interface GenreDao {
    long count();

    void delete(long id);

    List<Genre> getAll();

    Genre getGenreById(Long id);

    void insert(Genre object);

    void update(Genre object);

    long generateId();
}
