package ru.otus.homework.hw09.service;

import ru.otus.homework.hw09.entity.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllByIsbn(String isbn);

    void delete(long commentId);

}
