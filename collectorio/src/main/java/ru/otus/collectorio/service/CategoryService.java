package ru.otus.collectorio.service;


import ru.otus.collectorio.payload.request.category.CategoryExtRequest;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.category.CategoryExtResponse;
import ru.otus.collectorio.payload.response.category.CategoryHierarchicalResponse;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryHierarchicalResponse> getAllCategoryHierarchy();

    CategoryResponse save(CategoryRequest category);

    CategoryExtResponse save(CategoryExtRequest category);

    void deleteById(Long id);
}
