package ru.otus.homework.repository;

import ru.otus.homework.entity.Genre;

import java.util.List;

public interface GenreRepository {

    void deleteById(long id);

    List<Genre> getAll(); 

    List<Genre> getGenresByIsbn(String isbn);

    Genre getGenreById(Long id);

    Genre getGenreByName(String genreName);

    boolean isAttachedToBook(long id); 

    Genre save(Genre object);
}
