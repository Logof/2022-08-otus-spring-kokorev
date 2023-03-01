package ru.otus.homework.hw16.service;

import ru.otus.homework.hw16.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    void delete(Long authorId);

    AuthorDto add(String fullName);

    List<AuthorDto> getAll();
}
