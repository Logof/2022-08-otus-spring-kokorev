package ru.otus.homework.hw10.service;

import ru.otus.homework.hw10.entity.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllByIsbn(String isbn);

    void delete(long commentId);

}
