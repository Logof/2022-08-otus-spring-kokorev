package ru.otus.homework.hw16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw16.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook_Id(Long isbn);
}
