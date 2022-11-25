package ru.otus.homework.hw06.service;

public interface CommentService {

    void getAllByIsbn(String isbn);

    void delete(long commentId);

}
