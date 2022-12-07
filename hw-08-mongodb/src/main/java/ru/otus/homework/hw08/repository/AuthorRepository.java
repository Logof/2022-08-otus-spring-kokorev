package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByFullNameLike(String fullName);

    Author findByFullName(String fullName);
}
