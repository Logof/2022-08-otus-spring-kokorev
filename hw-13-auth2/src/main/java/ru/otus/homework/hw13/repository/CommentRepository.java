package ru.otus.homework.hw13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw13.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook_Id(Long isbn);
}
