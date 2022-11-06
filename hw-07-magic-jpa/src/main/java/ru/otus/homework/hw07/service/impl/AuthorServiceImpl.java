package ru.otus.homework.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.hw07.entity.Author;
import ru.otus.homework.hw07.entity.dto.AuthorDto;
import ru.otus.homework.hw07.mapper.AuthorMapper;
import ru.otus.homework.hw07.repository.AuthorRepository;
import ru.otus.homework.hw07.service.AuthorService;
import ru.otus.homework.hw07.service.print.PrintService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final PrintService<AuthorDto> printService;

    private final AuthorMapper mapper;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(PrintService<AuthorDto> printService,
                             AuthorMapper mapper,
                             AuthorRepository authorRepository) {
        this.printService = printService;
        this.mapper = mapper;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void delete(long authorId) {
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
