package com.github.logof.collectorio.service;

import com.github.logof.collectorio.entitty.game.Platform;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PlatformService {
    List<Platform> findAll();

    Platform findById(String code) throws EntityNotFoundException;

    Platform save(Platform entity);

    void deleteById(String code);
}
