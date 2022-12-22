package com.github.logof.collectorio.controller;

import com.github.logof.collectorio.entitty.game.Platform;
import com.github.logof.collectorio.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlatformRestController {

    private final PlatformService platformService;

    @GetMapping(path = "/record-data/platform")
    public List<Platform> findAll() {
        return platformService.findAll();
    }

    @GetMapping(path = "/record-data/platform/{code}")
    public Platform findById(@PathVariable String code) {
        return platformService.findById(code);
    }

    @PostMapping(path = "/record-data/platform")
    public void save(@RequestBody Platform entity) {
        platformService.save(entity);
    }

    @DeleteMapping(path = "/record-data/platform/{code}")
    public void deleteById(@PathVariable String code) {
        platformService.deleteById(code);
    }
}

