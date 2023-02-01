package ru.otus.collectorio.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.entity.ItemCard;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.request.item.ItemFindRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.ItemCardService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemCardController {

    private final ItemCardService itemCardService;

    public ItemCardController(ItemCardService itemCardService) {
        this.itemCardService = itemCardService;
    }


    //TODO отсылать ItemListResponse
    @GetMapping(path = "/api/item/{id}")
    @Transactional(readOnly = true)
    public EntityResponse<ItemCard> getItemById(@PathVariable Long id) {
        try {
            return EntityResponse.success(itemCardService.findById(id));
        } catch (DataNotFoundException ex) {
            return EntityResponse.error(ex.getMessage());
        }

    }

    //TODO отсылать ItemListResponse
    @PostMapping(path = "/api/item")
    public EntityResponse<ItemCard> saveItem(@RequestBody ItemCard itemCard) {
        try {
            return EntityResponse.success(itemCardService.save(itemCard));
        } catch (Exception e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/find")
    public EntityResponse<List<ItemCard>> findByName(@RequestBody ItemFindRequest itemFindRequest) {
        List<ItemCard> result = new ArrayList<>();
        result.addAll(itemCardService.findAllByQuery(itemFindRequest.getName(),
                itemFindRequest.getCategory()));
        return EntityResponse.success(result);
    }

    @PostMapping(path = "/{id}")
    public EntityResponse deleteItemById(@PathVariable Long id) {
        try {
            itemCardService.deleteById(id);
            return EntityResponse.success();
        } catch (Exception e) {
            return EntityResponse.error(e.getMessage());
        }
    }
}
