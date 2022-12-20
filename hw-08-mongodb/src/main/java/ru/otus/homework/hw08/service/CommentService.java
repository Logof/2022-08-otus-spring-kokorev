package ru.otus.homework.hw08.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw08.entity.Comment;
import ru.otus.homework.hw08.exception.DeleteDataException;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByIsbn(String isbn);

    void deleteCommentByIndex(String isbn, int index) throws DeleteDataException;

    @Transactional
    void addCommentToBook(String isbn, String commentText);
}
