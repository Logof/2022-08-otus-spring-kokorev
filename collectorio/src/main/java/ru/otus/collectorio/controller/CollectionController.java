package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CollectionService;

@RestController
@Tag(name = "Коллекции")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping(path = "/api/collections")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getAllCollections(){
        try {
            return EntityResponse.success(collectionService.findAll());
        } catch (DataNotFoundException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/collections")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse save(@RequestBody CollectionRequest item){
        return EntityResponse.success(collectionService.save(item));
    }

    @DeleteMapping(path = "/api/collections/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse delete(@PathVariable Long id){
        collectionService.deleteById(id);
        return EntityResponse.success();
    }
}
