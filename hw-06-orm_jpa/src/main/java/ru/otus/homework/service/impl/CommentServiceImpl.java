package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Comment;
import ru.otus.homework.repositories.CommentRepository;
import ru.otus.homework.service.CommentService;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCommentFromBook(Book book, long commentId) {
        Optional<Comment> comment = repository.findById(commentId);
        if (comment.isPresent()) {
            book.getComments().remove(comment.get());
        }
    }

    @Override
    public void deleteComment(long commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public void addCommentToBook(Book book, Comment comment) {
        if (comment.getId() == null) {
            comment = repository.save(comment);
        } else {
            Optional<Comment> foundComment = repository.findById(comment.getId());
            if (foundComment.isEmpty()) {
                comment = repository.save(comment);
            }
        }
        book.getComments().add(comment);
    }

    @Override
    public void outputAll(String isbn) {
        repository.findAll();
    }
}
