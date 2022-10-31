package ru.otus.homework.hw07.service;

import ru.otus.homework.hw07.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllByIsbn(String isbn);

    void delete(long commentId);

}
