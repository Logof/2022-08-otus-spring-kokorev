package ru.otus.homework.hw10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw10.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBook_Isbn(String isbn);
}
