package ru.otus.homework.hw07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw07.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreNameLike(String genreName);
}
