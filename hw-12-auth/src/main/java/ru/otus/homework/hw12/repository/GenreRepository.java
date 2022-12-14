package ru.otus.homework.hw12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw12.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreNameLike(String genreName);
}
