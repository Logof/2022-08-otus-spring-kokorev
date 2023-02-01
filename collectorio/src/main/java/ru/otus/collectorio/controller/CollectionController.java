package ru.otus.collectorio.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CollectibleItemService;
import ru.otus.collectorio.service.CollectionService;

import java.util.List;

@RestController
public class CollectionController {

    private final CollectionService collectionService;

    private final CollectibleItemService collectibleItemService;

    public CollectionController(CollectionService collectionService,
                                CollectibleItemService collectibleItemService) {
        this.collectionService = collectionService;
        this.collectibleItemService = collectibleItemService;
    }

    @GetMapping(path = "/api/collections/{id}")
    public EntityResponse<CollectibleItem> getCollectibleItemInCollection(@PathVariable Long id){
        try {
            return EntityResponse.success(collectibleItemService.findByCollection(id));
        } catch (DataNotFoundException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @GetMapping(path = "/api/collections")
    public EntityResponse<Collection> getCollectionById(){
        try {
            return EntityResponse.success(collectionService.findAll());
        } catch (DataNotFoundException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/collections")
    public EntityResponse<List<Collection>> save(@RequestBody Collection item){
        return EntityResponse.success(collectionService.save(item));
    }

    @PostMapping(path = "/api/collections/{id}")
    public EntityResponse<List<Collection>> delete(@PathVariable Long id){
        collectionService.deleteById(id);
        return EntityResponse.success();
    }
}
