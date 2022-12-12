package ru.otus.homework.hw11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw11.entity.Author;
import ru.otus.homework.hw11.entity.Book;
import ru.otus.homework.hw11.entity.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookMongodbRepository {

    List<Book> findAllByGenres(Genre genre);

    List<Book> findAllByAuthors(Author author);

    void updateDocumentTitle(String id, String title);

}