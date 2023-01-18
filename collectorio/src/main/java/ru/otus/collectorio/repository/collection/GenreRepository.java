package ru.otus.collectorio.repository.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.collection.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
