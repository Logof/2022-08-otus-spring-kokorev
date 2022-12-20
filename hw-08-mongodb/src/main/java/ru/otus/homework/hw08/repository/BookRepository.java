package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookMongodbRepository {

    List<Book> findAllByGenres(Genre genre);

    boolean existsByGenres(Genre genre);

    List<Book> findAllByAuthors(Author author);

    boolean existsByAuthors(Author author);

    void updateDocumentTitle(String id, String title);

}