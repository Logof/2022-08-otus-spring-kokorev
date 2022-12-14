package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Author;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends MongoRepository<Author, UUID> {
    Optional<Author> findByFullNameLike(String fullName);

    Optional<Author> findByFullName(String fullName);
}
