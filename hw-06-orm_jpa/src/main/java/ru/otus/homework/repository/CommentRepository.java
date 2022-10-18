package ru.otus.homework.repository;

import ru.otus.homework.entity.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    void deleteById(long id);

    List<Comment> getAllByIsbn(String isbn);
}
