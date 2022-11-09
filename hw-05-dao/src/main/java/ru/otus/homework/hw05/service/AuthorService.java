package ru.otus.homework.hw05.service;

import ru.otus.homework.hw05.entity.Author;

public interface AuthorService {

    Author add(String fullName);

    void delete(long authorId);

    void outputAll();
}
