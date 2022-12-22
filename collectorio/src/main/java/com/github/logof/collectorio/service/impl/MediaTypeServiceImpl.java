package com.github.logof.collectorio.service.impl;

import com.github.logof.collectorio.entitty.game.MediaType;
import com.github.logof.collectorio.repository.MediaTypeRepository;
import com.github.logof.collectorio.service.MediaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaTypeServiceImpl implements MediaTypeService {

    private final MediaTypeRepository repository;

    @Override
    public List<MediaType> findAll() {
        return repository.findAll();
    }

    @Override
    public MediaType findById(String code) throws EntityNotFoundException {
        return repository.findById(code).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public MediaType save(MediaType entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(String code) {
        repository.deleteById(code);
    }
}
