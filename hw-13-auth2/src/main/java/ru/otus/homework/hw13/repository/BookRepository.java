package ru.otus.homework.hw13.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import ru.otus.homework.hw13.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Book getById(@NonNull Long id);

    Book save(@NonNull Book book);

    void deleteById(@NonNull Long isbn);
}
