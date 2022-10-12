package ru.otus.homework.dao;

import ru.otus.homework.entity.Genre;

import java.util.List;

public interface GenreDao {
    long count();

    int delete(long id);

    List<Genre> getAll();

    Genre getGenreById(Long id);

    int insert(Genre object);

    int update(Genre object);

    long generateId();
}
