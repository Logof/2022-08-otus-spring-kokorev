package ru.otus.homework.hw13.service;

import ru.otus.homework.hw13.entity.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllByIsbn(long isbn);

    void delete(long commentId);

}
