package com.github.logof.collectorio.service;

import com.github.logof.collectorio.entitty.game.VideoGame;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

public interface VideoGameService {
    List<VideoGame> findAll();

    VideoGame findById(UUID id) throws EntityNotFoundException;

    VideoGame save(VideoGame entity);

    void deleteById(UUID id);
}
