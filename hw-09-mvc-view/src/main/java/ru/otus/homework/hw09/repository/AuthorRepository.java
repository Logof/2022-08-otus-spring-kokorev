package ru.otus.homework.hw09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw09.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFullNameLike(String fullName);
}
