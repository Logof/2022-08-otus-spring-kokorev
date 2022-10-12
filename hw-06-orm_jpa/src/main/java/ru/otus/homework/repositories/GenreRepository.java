package ru.otus.homework.repositories;

import ru.otus.homework.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre author);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    void updateNameById(long id, String name);

    void deleteById(long id);
}
