package ru.otus.homework.hw16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw16.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
