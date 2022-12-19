package ru.otus.homework.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw14.entity.mongo.GenreDocument;

public interface MongoGenreRepository extends MongoRepository<GenreDocument, String> {
}
