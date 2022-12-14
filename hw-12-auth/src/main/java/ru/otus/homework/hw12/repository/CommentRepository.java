package ru.otus.homework.hw12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw12.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBook_Isbn(String isbn);
}
