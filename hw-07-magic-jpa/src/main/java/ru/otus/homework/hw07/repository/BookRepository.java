package ru.otus.homework.hw07.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.entity.Book;
import ru.otus.homework.hw07.entity.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    @EntityGraph(value = "bookWithAllFields",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();

    @Query("Select b from Book b where :author member b.authors")
    List<Book> findAllByAuthor(@Param("author") Author fullName);

    @Query("Select b from Book b where :genre member b.genres")
    List<Book> findAllByGenre(@Param("genre") Genre genreName);
}
