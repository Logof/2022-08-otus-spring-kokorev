package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CollectionService;

@RestController
@Tag(name = "Коллекции")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping(path = "/api/collections/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getCollectibleItemInCollection(@PathVariable Long id){
        try {
            return EntityResponse.success(collectionService.findCollectableItemByCollectionId(id));
        } catch (DataNotFoundException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @GetMapping(path = "/api/collections")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getCollectionById(){
        try {
            return EntityResponse.success(collectionService.findAll());
        } catch (DataNotFoundException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/collections")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse save(@RequestBody Collection item){
        return EntityResponse.success(collectionService.save(item));
    }

    @PostMapping(path = "/api/collections/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse delete(@PathVariable Long id){
        collectionService.deleteById(id);
        return EntityResponse.success();
    }
}
