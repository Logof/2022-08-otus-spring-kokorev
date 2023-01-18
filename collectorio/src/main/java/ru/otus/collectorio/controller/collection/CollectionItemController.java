package ru.otus.collectorio.controller.collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.collectorio.dto.collection.CollectionItemDto;
import ru.otus.collectorio.service.collection.CollectionItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/collections")
public class CollectionItemController {

    private final CollectionItemService collectionItemService;

    public CollectionItemController(CollectionItemService collectionItemService) {
        this.collectionItemService = collectionItemService;
    }

    @GetMapping(path = "/items")
    public List<CollectionItemDto> getAllCollectionItem() {
        return collectionItemService.findAll();
    }

    @GetMapping(path = "/items/{id}")
    public CollectionItemDto getCollectionItemById(@PathVariable("id") Long id) {
        try {
            return collectionItemService.findById(id);
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
            return null;
        }
    }
}
