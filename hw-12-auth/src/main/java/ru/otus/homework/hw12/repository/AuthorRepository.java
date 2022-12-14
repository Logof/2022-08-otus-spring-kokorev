package ru.otus.homework.hw12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw12.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFullNameLike(String fullName);
}
