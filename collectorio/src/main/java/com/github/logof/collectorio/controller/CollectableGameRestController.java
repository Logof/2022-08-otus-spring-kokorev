package com.github.logof.collectorio.controller;

import com.github.logof.collectorio.entitty.game.CollectableGame;
import com.github.logof.collectorio.entitty.game.dto.CollectableGameDto;
import com.github.logof.collectorio.service.CollectableGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CollectableGameRestController {

    private final CollectableGameService collectableGameService;

    @GetMapping(path = "/collection/game")
    public List<CollectableGameDto> findAll() {
        return collectableGameService.findAll();
    }

    @GetMapping(path = "/collection/game/{id}")
    public CollectableGameDto findById(@PathVariable UUID id) {
        return collectableGameService.findById(id);
    }

    @PostMapping(path = "/collection/game")
    public void save(@RequestBody CollectableGame entity) {
        collectableGameService.save(entity);
    }

    @DeleteMapping(path = "/collection/game/{id}")
    public void deleteById(@PathVariable UUID id) {
        collectableGameService.deleteById(id);
    }
}
