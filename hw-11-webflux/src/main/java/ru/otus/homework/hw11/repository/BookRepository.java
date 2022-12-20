package ru.otus.homework.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework.hw11.entity.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}