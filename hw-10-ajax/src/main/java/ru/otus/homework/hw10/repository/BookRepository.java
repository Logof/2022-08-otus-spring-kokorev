package ru.otus.homework.hw10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw10.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByOrderByTitleAsc();

    List<Book> findAllByAuthors_fullNameLike(String author);

    List<Book> findAllByGenres_genreNameLike(String genre);
}