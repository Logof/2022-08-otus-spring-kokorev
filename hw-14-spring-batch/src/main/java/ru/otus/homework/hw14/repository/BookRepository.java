package ru.otus.homework.hw14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw14.entity.h2.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}