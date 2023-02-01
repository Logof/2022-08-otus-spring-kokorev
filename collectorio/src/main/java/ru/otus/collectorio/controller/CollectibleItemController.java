package ru.otus.collectorio.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CollectibleItemService;

import java.util.List;

@RestController
public class CollectibleItemController {


    private final CollectibleItemService collectibleItemService;

    public CollectibleItemController(CollectibleItemService collectibleItemService) {
        this.collectibleItemService = collectibleItemService;
    }

    @GetMapping(path = "/api/collectibles")
    public EntityResponse<List<CollectibleItem>> getAllCollectibleItem(){
        return EntityResponse.success(collectibleItemService.findAll());
    }

    @GetMapping(path = "/api/collectibles/{id}")
    public EntityResponse<CollectibleItem> getCollectibleItemById(@PathVariable Long id){
        try {
            return EntityResponse.success(collectibleItemService.findById(id));
        } catch (DataNotFoundException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/collectibles")
    public EntityResponse<List<CollectibleItem>> save(@RequestBody CollectibleItem item){
        return EntityResponse.success(collectibleItemService.save(item));
    }

    @PostMapping(path = "/api/collectibles/{id}")
    public EntityResponse<List<CollectibleItem>> save(@PathVariable Long id){
        collectibleItemService.deleteById(id);
        return EntityResponse.success();
    }

}
