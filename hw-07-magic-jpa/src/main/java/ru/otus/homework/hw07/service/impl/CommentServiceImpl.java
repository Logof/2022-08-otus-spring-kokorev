package ru.otus.homework.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw07.entity.Comment;
import ru.otus.homework.hw07.repository.CommentRepository;
import ru.otus.homework.hw07.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByIsbn(String isbn) {
        return commentRepository.findAllByBook_Isbn(isbn);
    }

    @Override
    @Transactional
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
