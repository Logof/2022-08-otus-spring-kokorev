package ru.otus.homework.hw18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw18.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
