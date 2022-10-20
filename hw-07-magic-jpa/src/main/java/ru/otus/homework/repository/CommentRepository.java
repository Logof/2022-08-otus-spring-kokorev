package ru.otus.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework.entity.Comment;

import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteById(long id);

    //TODO
    @Query("select c from Comment c")
    Set<Comment> getAllByIsbn(String isbn);
}
