package ru.otus.homework.hw14.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw14.entity.mongo.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}