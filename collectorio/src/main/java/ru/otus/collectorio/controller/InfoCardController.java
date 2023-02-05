package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.request.item.ItemFindRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.InfoCardService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Информационные карты")
public class InfoCardController {

    private final InfoCardService infoCardService;

    public InfoCardController(InfoCardService infoCardService) {
        this.infoCardService = infoCardService;
    }

    //TODO отсылать ItemListResponse
    @GetMapping(path = "/api/item/{id}")
    @Operation(summary = "Извлечь карту по идентификатору")
    public EntityResponse getInfoCardById(@PathVariable Long id) {
        try {
            return EntityResponse.success(infoCardService.findById(id));
        } catch (DataNotFoundException ex) {
            return EntityResponse.error(ex.getMessage());
        }

    }

    //TODO отсылать ItemListResponse
    @PostMapping(path = "/api/item")
    @Operation(summary = "Сохранить информационную карту")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse saveInfoCard(@RequestBody InfoCard infoCard) {
        try {
            return EntityResponse.success(infoCardService.save(infoCard));
        } catch (Exception e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/find")
    @Operation(summary = "Поиск информационной карты по имени или категории")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse findInfoCardByName(@RequestBody ItemFindRequest itemFindRequest) {
        List<InfoCard> result = new ArrayList<>();
        result.addAll(infoCardService.findAllByQuery(itemFindRequest.getName(),
                itemFindRequest.getCategory()));
        return EntityResponse.success(result);
    }

    @PostMapping(path = "/api/{id}")
    @Operation(summary = "Удаляет информационную карту по Id")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse deleteInfoCardById(@PathVariable Long id) {
        try {
            infoCardService.deleteById(id);
            return EntityResponse.success();
        } catch (Exception e) {
            return EntityResponse.error(e.getMessage());
        }
    }
}
