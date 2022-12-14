package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Genre;

import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends MongoRepository<Genre, UUID> {
    Optional<Genre> findByGenreNameLike(String genreName);

}
