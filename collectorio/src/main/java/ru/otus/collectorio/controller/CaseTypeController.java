package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.exception.CategoryException;
import ru.otus.collectorio.payload.request.caseType.CaseTypeExtRequest;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CaseTypeService;

@RestController
@Tag(name = "Типы корпусов/упаковок")
public class CaseTypeController {
    private final CaseTypeService caseTypeService;

    public CaseTypeController(CaseTypeService caseTypeService) {
        this.caseTypeService = caseTypeService;
    }

    @GetMapping(path = "/api/categories/{id}/cases")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getCaseTypesByCategoryId(@PathVariable("id") Long id) {
        return EntityResponse.success(caseTypeService.getAllInCategory(id));
    }

    @PostMapping(path = "/api/cases")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse saveCategory(@RequestBody CaseTypeRequest caseType) {
        try {
            return EntityResponse.success(caseTypeService.save(caseType));
        } catch (CategoryException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @PostMapping(path = "/api/cases/ext")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse saveCategoryExt(@RequestBody CaseTypeExtRequest caseTypeExtRequest) {
        try {
            return EntityResponse.success(caseTypeService.save(caseTypeExtRequest));
        } catch (CategoryException e) {
            return EntityResponse.error(e.getMessage());
        }

    }

    @DeleteMapping(path = "/api/cases/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getCaseTypesById(@PathVariable("id") Long id) {
        return EntityResponse.success(caseTypeService.getAllInCategory(id));
    }
}
