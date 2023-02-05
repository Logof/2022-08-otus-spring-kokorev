package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CollectibleItemService;

@RestController
@Tag(name = "Коллекционный предмет")
public class CollectibleItemController {
    private final CollectibleItemService collectibleItemService;

    public CollectibleItemController(CollectibleItemService collectibleItemService) {
        this.collectibleItemService = collectibleItemService;
    }

    @GetMapping(path = "/api/collectibles")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getAllCollectibleItem(){
        return EntityResponse.success(collectibleItemService.findAll());
    }

    @GetMapping(path = "/api/collectibles/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getCollectibleItemById(@PathVariable Long id){
        try {
            return EntityResponse.success(collectibleItemService.findById(id));
        } catch (DataNotFoundException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/collectibles")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse save(@RequestBody CollectibleItemRequest item){
        return EntityResponse.success(collectibleItemService.save(item));
    }

    @PostMapping(path = "/api/collectibles/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse delete(@PathVariable Long id){
        collectibleItemService.deleteById(id);
        return EntityResponse.success();
    }

}
