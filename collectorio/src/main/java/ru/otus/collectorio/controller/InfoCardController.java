package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.payload.request.item.InfoCardExtRequest;
import ru.otus.collectorio.payload.request.item.InfoCardRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.InfoCardService;

@RestController
@Tag(name = "Информационные карты")
public class InfoCardController {

    private final InfoCardService infoCardService;

    public InfoCardController(InfoCardService infoCardService) {
        this.infoCardService = infoCardService;
    }

    @GetMapping(path = "/api/info/{id}")
    @Operation(summary = "Извлечь карту по идентификатору")
    public EntityResponse getInfoCardById(@PathVariable Long id) {
        try {
            return EntityResponse.success(infoCardService.findById(id));
        } catch (DataNotFoundException ex) {
            return EntityResponse.error(ex.getMessage());
        }

    }

    @PostMapping(path = "/api/info")
    @Operation(summary = "Сохранить информационную карту")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse saveInfoCard(@RequestBody InfoCardRequest infoCardRequest) {
        try {
            return EntityResponse.success(infoCardService.save(infoCardRequest));
        } catch (Exception e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/info/ext")
    @Operation(summary = "Сохранить информационную карту")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse saveInfoExtCard(@RequestBody InfoCardExtRequest infoCardExtRequest) {
        try {
            return EntityResponse.success(infoCardService.save(infoCardExtRequest));
        } catch (Exception e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @DeleteMapping(path = "/api/info/{id}")
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

    @Operation(summary = "Выводит информационные карты в заданной категории")
    @GetMapping(path = "/api/categories/{id}/info")
    public EntityResponse getPageFromCategory(@PathVariable("id") Long id) {
        return EntityResponse.success(infoCardService.getAllInCategory(id));
    }
}
