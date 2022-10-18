package ru.otus.homework.service;

public interface CommentService {


    void getAllByIsbn(String isbn);

    void add(String isbn, String commentText);

    void delete(long commentId);
}
