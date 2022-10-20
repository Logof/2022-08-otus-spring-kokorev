package ru.otus.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework.entity.Author;

import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author getByFullName(String fullName);

    void deleteById(long id);

    //TODO ???
    @Query("select a from Author a")
    Set<Author> getAuthorsBookByIsbn(String isbn);

    //TODO ???
    @Query("select a from Author a")
    boolean isAttachedToBook(long id);

}
