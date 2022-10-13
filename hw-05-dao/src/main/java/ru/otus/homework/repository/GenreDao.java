package ru.otus.homework.repository;

import ru.otus.homework.entity.Genre;

import java.util.List;

public interface GenreDao {
    long count();

    int delete(long id);

    List<Genre> getAll();

    List<Genre> getGenresByIsbn(String isbn);

    Genre getGenreById(Long id);

    boolean isAttachedToBook(long id);

    int insert(Genre object);

    int update(Genre object);

    long generateId();
}
