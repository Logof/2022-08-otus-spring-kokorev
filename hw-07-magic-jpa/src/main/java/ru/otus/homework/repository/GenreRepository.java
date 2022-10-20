package ru.otus.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework.entity.Genre;

import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    void deleteById(long id);


    //TODO
    @Query("select g from Genre g")
    Genre getGenreByName(String genreName);

    //TODO
    @Query("select g from Genre g")
    Set<Genre> getGenresByIsbn(String isbn);

    //TODO
    @Query("select g from Genre g")
    boolean isAttachedToBook(long id);
}
