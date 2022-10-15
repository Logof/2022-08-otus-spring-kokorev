package ru.otus.homework.service;

import ru.otus.homework.entity.Author;

public interface AuthorService {

    void add(String fullName);

    void delete(long authorId);

    Author findByAuthorFullName(String fullName);

    void outputAll();
}
