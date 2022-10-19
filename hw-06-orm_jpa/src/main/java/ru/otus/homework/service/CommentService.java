package ru.otus.homework.service;

public interface CommentService {


    void getAllByIsbn(String isbn);

    void delete(long commentId);
}
