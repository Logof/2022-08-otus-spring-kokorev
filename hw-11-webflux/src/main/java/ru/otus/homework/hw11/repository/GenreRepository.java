package ru.otus.homework.hw11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw11.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByGenreNameLike(String genreName);
}