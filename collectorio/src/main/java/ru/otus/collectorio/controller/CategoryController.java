package ru.otus.collectorio.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.service.CategoryService;
import ru.otus.collectorio.service.ItemCardService;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    private final ItemCardService itemCardService;

    public CategoryController(CategoryService categoryService,
                              ItemCardService itemCardService) {
        this.categoryService = categoryService;
        this.itemCardService = itemCardService;
    }

    @GetMapping(path = "/api/categories")
    public EntityResponse<List<Category>> getAllCollections() {
        return EntityResponse.success(categoryService.getAllCategory());
    }

    @GetMapping(path = "/api/categories/{id}")
    public EntityResponse getPageFromCategory(@PathVariable("id") Long id) {
        return EntityResponse.success(itemCardService.getAllInCategory(id));
    }

    @PostMapping(path = "/api/categories")
    public EntityResponse<Category> saveCategory(@RequestBody Category category) {
        return EntityResponse.success(categoryService.saveCategory(category));
    }

    @PostMapping(path = "/api/categories/:id")
    public EntityResponse deleteCategory(@RequestParam Long id) {
        categoryService.deleteById(id);
        return EntityResponse.success();
    }


}
