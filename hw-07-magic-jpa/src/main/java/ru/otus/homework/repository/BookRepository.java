package ru.otus.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework.entity.Book;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book, String> {

    void deleteByIsbn(String isbn);

    //TODO
    @Query("select b from Book b")
    Set<Book> getAllByAuthor(String fullName);

    //TODO
    @Query("select b from Book b")
    Set<Book> getAllByGenre(String genreName);
}
