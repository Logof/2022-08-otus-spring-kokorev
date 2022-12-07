package ru.otus.homework.hw08.service;

import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.exception.DeleteDataException;

import java.util.List;

public interface AuthorService {

    Author add(String fullName);

    void delete(String fullName) throws DeleteDataException;

    List<Author> getAll();
}
