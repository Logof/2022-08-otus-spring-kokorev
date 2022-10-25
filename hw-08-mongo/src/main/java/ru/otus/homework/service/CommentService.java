package ru.otus.homework.service;

import ru.otus.homework.entity.Comment;

public interface CommentService {


    void getAllByIsbn(String isbn);

    void delete(String commentId);

    Comment createAndSave(String commentText, String isbn);
}
