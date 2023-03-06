package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CategoryService;

@RestController
@Tag(name = "Категории/разделы")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/api/categories")
    public EntityResponse getAllCollections() {
        return EntityResponse.success(categoryService.getAllCategoryHierarchy());
    }

    @PostMapping(path = "/api/categories")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse saveCategory(@RequestBody CategoryRequest category) {
        try {
            return EntityResponse.success(categoryService.save(category));
        } catch (RuntimeException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @DeleteMapping(path = "/api/categories/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryService.deleteById(id);
            return EntityResponse.success();
        } catch (RuntimeException e) {
            return EntityResponse.error("Удалять категории можно только если в ней не содержится данных");
        }

    }


}
