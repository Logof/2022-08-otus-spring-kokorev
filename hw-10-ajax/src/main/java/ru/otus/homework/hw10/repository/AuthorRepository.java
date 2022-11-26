package ru.otus.homework.hw10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw10.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFullNameLike(String fullName);
}
