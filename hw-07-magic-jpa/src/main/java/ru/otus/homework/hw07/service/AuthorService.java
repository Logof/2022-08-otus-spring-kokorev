package ru.otus.homework.hw07.service;

import ru.otus.homework.hw07.entity.Author;

import java.util.List;

public interface AuthorService {

    Author add(String fullName);

    void delete(long authorId);

    List<Author> getAll();
}
