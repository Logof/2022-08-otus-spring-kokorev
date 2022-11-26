package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthors(String author);

    List<Book> findAllByGenres(String genre);
}