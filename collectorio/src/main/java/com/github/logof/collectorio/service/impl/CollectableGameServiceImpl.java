package com.github.logof.collectorio.service.impl;

import com.github.logof.collectorio.entitty.game.CollectableGame;
import com.github.logof.collectorio.entitty.game.dto.CollectableGameDto;
import com.github.logof.collectorio.mapper.game.CollectableGameMapper;
import com.github.logof.collectorio.repository.CollectableGameRepository;
import com.github.logof.collectorio.service.CollectableGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectableGameServiceImpl implements CollectableGameService {

    private final CollectableGameRepository repository;

    private final CollectableGameMapper mapper;

    @Override
    public List<CollectableGameDto> findAll() {
        return mapper.toDtos(repository.findAll());
    }

    @Override
    public CollectableGameDto findById(UUID id) throws EntityNotFoundException {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }
    @Override
    public CollectableGame save(CollectableGame entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(CollectableGame entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
