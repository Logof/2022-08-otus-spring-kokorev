package com.github.logof.collectorio.service;

import com.github.logof.collectorio.entitty.game.CollectableGame;
import com.github.logof.collectorio.entitty.game.dto.CollectableGameDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

public interface CollectableGameService {
    List<CollectableGameDto> findAll();

    CollectableGameDto findById(UUID id) throws EntityNotFoundException;

    CollectableGame save(CollectableGame collectableGame);

    void delete(CollectableGame collectableGame);

    void deleteById(UUID id);
}
