package com.github.logof.collectorio.service.impl;

import com.github.logof.collectorio.entitty.game.VideoGame;
import com.github.logof.collectorio.repository.VideoGameRepository;
import com.github.logof.collectorio.service.VideoGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoGameServiceImpl implements VideoGameService {
    private final VideoGameRepository repository;

    @Override
    public List<VideoGame> findAll() {
        return repository.findAll();
    }

    @Override
    public VideoGame findById(UUID id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public VideoGame save(VideoGame entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}

