package ru.otus.homework.dao;

import ru.otus.homework.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    long count();

    int delete(long id);

    List<Genre> getAll();

    Optional<Genre> getGenreById(Long id);

    int insert(Genre object);

    int update(Genre object);

    long generateId();
}
