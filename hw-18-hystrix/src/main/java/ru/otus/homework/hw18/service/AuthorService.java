package ru.otus.homework.hw18.service;

import ru.otus.homework.hw18.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    void delete(Long authorId);

    AuthorDto add(String fullName);

    List<AuthorDto> getAll();
}
