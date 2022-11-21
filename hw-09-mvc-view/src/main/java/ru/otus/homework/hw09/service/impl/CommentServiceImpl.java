package ru.otus.homework.hw09.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw09.entity.dto.CommentDto;
import ru.otus.homework.hw09.mapper.CommentMapper;
import ru.otus.homework.hw09.repository.CommentRepository;
import ru.otus.homework.hw09.service.CommentService;

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
    public List<CommentDto> getAllByIsbn(String isbn) {
        return mapper.toDtos(commentRepository.findAllByBook_Isbn(isbn));
    }

    @Override
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
