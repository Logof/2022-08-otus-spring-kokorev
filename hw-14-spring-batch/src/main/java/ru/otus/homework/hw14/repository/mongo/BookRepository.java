package ru.otus.homework.hw14.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw14.entity.mongo.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
