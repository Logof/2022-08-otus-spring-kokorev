package ru.otus.homework.hw13.service;

import ru.otus.homework.hw13.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    void delete(long authorId);

    AuthorDto add(String fullName);

    List<AuthorDto> getAll();
}
