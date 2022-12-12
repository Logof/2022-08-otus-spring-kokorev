package ru.otus.homework.hw11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw11.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByFullNameLike(String fullName);

    Optional<Author> findByFullName(String fullName);
}