package ru.otus.homework.repositories;

import ru.otus.homework.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    void updateFullNameById(long id, String fullName);

    void deleteById(long id);
}
