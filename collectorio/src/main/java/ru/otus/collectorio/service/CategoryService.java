package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.request.category.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    Category saveCategory(CategoryRequest category);

    void deleteById(Long id);
}
