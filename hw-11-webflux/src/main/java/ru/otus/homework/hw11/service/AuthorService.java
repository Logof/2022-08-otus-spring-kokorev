package ru.otus.homework.hw11.service;

import ru.otus.homework.hw11.entity.Author;
import ru.otus.homework.hw11.exception.DeleteDataException;

import java.util.List;

public interface AuthorService {

    Author add(String fullName);

    void delete(String fullName) throws DeleteDataException;

    List<Author> getAll();
}
