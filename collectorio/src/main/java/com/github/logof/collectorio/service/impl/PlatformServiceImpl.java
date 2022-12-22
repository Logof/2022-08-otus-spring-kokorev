package com.github.logof.collectorio.service.impl;

import com.github.logof.collectorio.entitty.game.Platform;
import com.github.logof.collectorio.repository.PlatformRepository;
import com.github.logof.collectorio.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository repository;

    @Override
    public List<Platform> findAll() {
        return repository.findAll();
    }

    @Override
    public Platform findById(String code) throws EntityNotFoundException {
        return repository.findById(code).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public Platform save(Platform entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(String code) {
        repository.deleteById(code);
    }
}
