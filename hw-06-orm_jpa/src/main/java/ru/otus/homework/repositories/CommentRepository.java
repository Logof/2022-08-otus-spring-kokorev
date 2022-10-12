package ru.otus.homework.repositories;

import ru.otus.homework.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment author);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    void updateCommentById(long id, String commentText);

    void deleteById(long id);
}
