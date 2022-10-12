package ru.otus.homework.service;

import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Comment;

public interface CommentService {
    void deleteCommentFromBook(Book book, long commentId);

    void deleteComment(long commentId);

    void addCommentToBook(Book book, Comment comment);

    void outputAll(String isbn);
}
