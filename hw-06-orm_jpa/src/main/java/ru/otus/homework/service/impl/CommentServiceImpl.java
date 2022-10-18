package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Comment;
import ru.otus.homework.repository.CommentRepository;
import ru.otus.homework.service.CommentService;
import ru.otus.homework.service.OutputService;
import ru.otus.homework.service.print.PrintService;

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
    public void getAllByIsbn(String isbn) {
        ioService.outString(printService.objectsToPrint(commentRepository.getAllByIsbn(isbn)));
    }

    @Override
    public void add(String isbn, String commentText) {
        Comment comment = commentRepository.save(new Comment(commentText));
        ioService.outString(String.format("Comment added. ID: %d", comment.getId()));
    }

    @Override
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
        ioService.outString("Entry deleted");
    }
}
