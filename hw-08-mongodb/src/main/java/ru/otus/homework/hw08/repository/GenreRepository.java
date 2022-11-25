package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByGenreNameLike(String genreName);
}
