package ru.otus.homework.service;

import ru.otus.homework.entity.Author;

public interface AuthorService {

    Author findOrCreate(String fullName);

    Author save(Author author);

    void delete(String fullName);

    void outputAll();
}
