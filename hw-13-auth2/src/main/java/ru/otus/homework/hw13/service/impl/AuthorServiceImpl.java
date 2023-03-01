package ru.otus.homework.hw13.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw13.dto.AuthorDto;
import ru.otus.homework.hw13.entity.Author;
import ru.otus.homework.hw13.mapper.AuthorMapper;
import ru.otus.homework.hw13.repository.AuthorRepository;
import ru.otus.homework.hw13.service.AuthorService;

import java.util.List;

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
        return mapper.toDto(authorRepository.save(new Author(null, fullName)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAll() {
        return mapper.toDtos(authorRepository.findAll());
    }
}
