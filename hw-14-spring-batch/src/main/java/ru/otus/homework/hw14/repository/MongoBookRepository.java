package ru.otus.homework.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw14.entity.mongo.BookDocument;

public interface MongoBookRepository extends MongoRepository<BookDocument, String> {
}
