package ru.otus.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework.entity.Author;


public interface AuthorRepository extends MongoRepository<Author, String> {

    @Query(value = "{'id' : $id}", delete = true)
    void deleteById(@Param("id") String id);

}
