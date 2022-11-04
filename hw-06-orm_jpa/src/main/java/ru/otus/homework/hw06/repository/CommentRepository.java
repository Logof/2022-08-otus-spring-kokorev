package ru.otus.homework.hw06.repository;

import ru.otus.homework.hw06.entity.Comment;

import java.util.Set;

public interface CommentRepository {

    Comment save(Comment comment);

    void deleteById(long id);

    Set<Comment> getAllByIsbn(String isbn);
}
