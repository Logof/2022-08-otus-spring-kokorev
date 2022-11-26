package ru.otus.homework.hw08.service;

import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByIsbn(String isbn);

    Book deleteCommentByIndex(String isbn, int index);

}
