package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Author;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String>, BookMongodbRepository {

    List<Book> findAllByGenres(Genre genre);

    Optional<Book> findOneByGenres(Genre genre);

    List<Book> findAllByAuthors(Author author);

    Optional<Book> findOneByAuthors(Author author);

    void updateDocumentTitle(String id, String title);

}