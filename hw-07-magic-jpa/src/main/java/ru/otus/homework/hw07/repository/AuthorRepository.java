package ru.otus.homework.hw07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw07.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFullNameLike(String fullName);
}
