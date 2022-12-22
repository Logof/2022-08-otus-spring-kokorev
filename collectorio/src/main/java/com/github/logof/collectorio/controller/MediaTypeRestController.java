package com.github.logof.collectorio.controller;

import com.github.logof.collectorio.entitty.game.MediaType;
import com.github.logof.collectorio.service.MediaTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MediaTypeRestController {

    private final MediaTypeService mediaTypeService;

    @GetMapping(path = "/record-data/media")
    public List<MediaType> findAll() {
        return mediaTypeService.findAll();
    }

    @GetMapping(path = "/record-data/media/{code}")
    public MediaType findById(@PathVariable String code) {
        return mediaTypeService.findById(code);
    }

    @PostMapping(path = "/record-data/media")
    public void save(@RequestBody MediaType entity) {
        mediaTypeService.save(entity);
    }

    @DeleteMapping(path = "/record-data/media/{code}")
    public void deleteById(@PathVariable String code) {
        mediaTypeService.deleteById(code);
    }
}
