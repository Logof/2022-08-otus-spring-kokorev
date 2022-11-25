package ru.otus.homework.hw08.service;

import ru.otus.homework.hw08.entity.Author;

import java.util.List;

public interface AuthorService {

    Author add(String fullName);

    void delete(String fullName);

    List<Author> getAll();
}
