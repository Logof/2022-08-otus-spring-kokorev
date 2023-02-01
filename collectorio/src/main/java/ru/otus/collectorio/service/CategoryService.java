package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    Category saveCategory(Category category);

    void deleteById(Long id);
}
