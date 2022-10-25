package ru.otus.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("select b from Book b where authors.fullName like = :fullName")
    List<Book> findByAuthor(@Param("fullName") String fullName);

    @Query("select b from Book b where genres.genreName like = :genreName")
    List<Book> getAllByGenre(String genreName);
}
