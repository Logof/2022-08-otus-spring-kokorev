package com.github.logof.collectorio.controller;

import com.github.logof.collectorio.entitty.game.VideoGame;
import com.github.logof.collectorio.service.VideoGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VideoGameRestController {

    private final VideoGameService platformService;

    @GetMapping(path = "/record-data/game")
    public List<VideoGame> findAll() {
        return platformService.findAll();
    }

    @GetMapping(path = "/record-data/game/{id}")
    public VideoGame findById(@PathVariable UUID id) {
        return platformService.findById(id);
    }

    @PostMapping(path = "/record-data/game")
    public void save(@RequestBody VideoGame entity) {
        platformService.save(entity);
    }

    @DeleteMapping(path = "/record-data/game/{id}")
    public void deleteById(@PathVariable UUID id) {
        platformService.deleteById(id);
    }
}

