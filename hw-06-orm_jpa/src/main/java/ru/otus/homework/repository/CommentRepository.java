package ru.otus.homework.repository;

import ru.otus.homework.entity.Comment;

import java.util.Set;

public interface CommentRepository {

    Comment save(Comment comment);

    void deleteById(long id);

    Set<Comment> getAllByIsbn(String isbn);
}
