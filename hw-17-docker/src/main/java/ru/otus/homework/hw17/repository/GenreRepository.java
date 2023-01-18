package ru.otus.homework.hw17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw17.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
