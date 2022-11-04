package ru.otus.homework.hw06.service;

import ru.otus.homework.hw06.entity.Author;

public interface AuthorService {

    Author add(String fullName);

    void delete(long authorId);

    void outputAll();
}
