package ru.otus.homework.hw18.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw18.dto.AuthorDto;
import ru.otus.homework.hw18.entity.Author;
import ru.otus.homework.hw18.exception.FieldRequiredException;
import ru.otus.homework.hw18.mapper.AuthorMapper;
import ru.otus.homework.hw18.repository.AuthorRepository;
import ru.otus.homework.hw18.service.AuthorService;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper mapper;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorMapper mapper,
                             AuthorRepository authorRepository) {
        this.mapper = mapper;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void delete(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    @Transactional
    public AuthorDto add(String fullName) {
        if (Objects.isNull(fullName) || fullName.isBlank()) {
            throw new FieldRequiredException("fullName");
        }
        return mapper.toDto(authorRepository.save(new Author(null, fullName)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAll() {
        return mapper.toDtos(authorRepository.findAll());
    }
}
