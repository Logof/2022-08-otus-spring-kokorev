package ru.otus.homework.hw10.service;

import ru.otus.homework.hw10.entity.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto add(String fullName);

    void delete(long authorId);

    List<AuthorDto> getAll();
}
