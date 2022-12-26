package ru.otus.homework.hw13.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw13.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    @Transactional
    void delete(long authorId);

    @Transactional
    AuthorDto add(String fullName);

    @Transactional(readOnly = true)
    List<AuthorDto> getAll();
}
