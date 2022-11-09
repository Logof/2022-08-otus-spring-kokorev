package ru.otus.homework.hw06.repository;

import ru.otus.homework.hw06.entity.Author;

import java.util.Set;

public interface AuthorRepository {
    Author getAuthorById(long id);

    Author getByFullName(String fullName);

    void delete(long id);

    Set<Author> getAll();

    boolean authorHasBooks(long id);

    Author save(Author object);
}
