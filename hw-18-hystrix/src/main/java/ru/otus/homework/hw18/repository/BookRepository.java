package ru.otus.homework.hw18.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw18.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    Book getById(@NonNull Long id);

    Book save(@NonNull Book book);

    void deleteById(@NonNull Long isbn);
}
