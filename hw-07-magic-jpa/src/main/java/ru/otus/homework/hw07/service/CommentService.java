package ru.otus.homework.hw07.service;

import ru.otus.homework.hw07.entity.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllByIsbn(String isbn);

    void delete(long commentId);

}
