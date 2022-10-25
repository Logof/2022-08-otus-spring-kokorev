package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.entity.Comment;
import ru.otus.homework.repository.CommentRepository;
import ru.otus.homework.service.CommentService;
import ru.otus.homework.service.OutputService;
import ru.otus.homework.service.print.PrintService;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PrintService<Comment> printService;

    private final OutputService ioService;

    public CommentServiceImpl(CommentRepository commentRepository, PrintService<Comment> printService, OutputService ioService) {
        this.commentRepository = commentRepository;
        this.printService = printService;
        this.ioService = ioService;
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllByIsbn(String isbn) {
        ioService.outString(printService.objectsToPrint(commentRepository.findByBookIsbn(isbn)));
    }

    @Override
    @Transactional
    public void delete(String commentId) {
        commentRepository.deleteById(commentId);
        ioService.outString("Entry deleted");
    }

    @Override
    public Comment createAndSave(String commentText, String isbn) {
        return commentRepository.save(new Comment(UUID.randomUUID().toString(), commentText, isbn));
    }
}
