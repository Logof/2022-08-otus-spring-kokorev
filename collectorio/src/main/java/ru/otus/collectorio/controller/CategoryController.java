package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CategoryService;
import ru.otus.collectorio.service.InfoCardService;

@RestController
@Tag(name = "Категории/разделы")
public class CategoryController {

    private final CategoryService categoryService;

    private final InfoCardService infoCardService;

    public CategoryController(CategoryService categoryService,
                              InfoCardService infoCardService) {
        this.categoryService = categoryService;
        this.infoCardService = infoCardService;
    }

    @GetMapping(path = "/api/categories")
    public EntityResponse getAllCollections() {
        return EntityResponse.success(categoryService.getAllCategory());
    }

    @GetMapping(path = "/api/categories/{id}")
    public EntityResponse getPageFromCategory(@PathVariable("id") Long id) {
        return EntityResponse.success(infoCardService.getAllInCategory(id));
    }

    @PostMapping(path = "/api/categories")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse saveCategory(@RequestBody CategoryRequest category) {
        return EntityResponse.success(categoryService.saveCategory(category));
    }

    @PostMapping(path = "/api/categories/:id")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse deleteCategory(@RequestParam Long id) {
        categoryService.deleteById(id);
        return EntityResponse.success();
    }


}
