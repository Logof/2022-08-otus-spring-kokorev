package ru.otus.homework.hw06.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw06.entity.Comment;
import ru.otus.homework.hw06.repository.CommentRepository;
import ru.otus.homework.hw06.service.CommentService;
import ru.otus.homework.hw06.service.OutputService;
import ru.otus.homework.hw06.service.print.PrintService;

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
        ioService.outString(printService.objectsToPrint(commentRepository.getAllByIsbn(isbn)));
    }

    @Override
    @Transactional
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
        ioService.outString("Entry deleted");
    }
}
