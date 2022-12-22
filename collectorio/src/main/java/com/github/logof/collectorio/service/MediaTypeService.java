package com.github.logof.collectorio.service;

import com.github.logof.collectorio.entitty.game.MediaType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface MediaTypeService {
    List<MediaType> findAll();

    MediaType findById(String code) throws EntityNotFoundException;

    MediaType save(MediaType entity);

    void deleteById(String code);
}
