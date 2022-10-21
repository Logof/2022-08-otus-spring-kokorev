package ru.otus.homework.service;

import ru.otus.homework.entity.Author;

public interface AuthorService {

    Author add(String fullName);

    void delete(long authorId);

    void outputAll();
}
