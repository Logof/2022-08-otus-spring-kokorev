package ru.otus.homework.hw12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw12.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByOrderByTitleAsc();

    List<Book> findAllByAuthors_fullNameLike(String author);

    List<Book> findAllByGenres_genreNameLike(String genre);
}