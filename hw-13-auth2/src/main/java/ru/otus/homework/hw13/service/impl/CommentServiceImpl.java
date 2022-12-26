package ru.otus.homework.hw13.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw13.entity.dto.CommentDto;
import ru.otus.homework.hw13.mapper.CommentMapper;
import ru.otus.homework.hw13.repository.CommentRepository;
import ru.otus.homework.hw13.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper mapper) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CommentDto> getAllByIsbn(long isbn) {
        return mapper.toDtos(commentRepository.findAllByBook_Id(isbn));
    }

    @Override
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
