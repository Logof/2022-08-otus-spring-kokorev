package ru.otus.homework.hw14.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw14.entity.h2.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
