package ru.otus.homework.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework.hw11.entity.Genre;

import java.util.UUID;

public interface GenreRepository extends ReactiveMongoRepository<Genre, UUID> {
}
