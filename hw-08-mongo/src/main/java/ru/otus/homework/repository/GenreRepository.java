package ru.otus.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
