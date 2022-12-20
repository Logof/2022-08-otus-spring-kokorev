package ru.otus.homework.hw08.service;

import ru.otus.homework.hw08.entity.Genre;
import ru.otus.homework.hw08.exception.DeleteDataException;

import java.util.List;

public interface GenreService {
    void delete(String genreName) throws DeleteDataException ;

    Genre add(String genreName);

    List<Genre> getAll();
}
