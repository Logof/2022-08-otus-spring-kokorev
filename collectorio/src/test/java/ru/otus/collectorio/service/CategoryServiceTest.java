package ru.otus.collectorio.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.repository.CategoryRepository;
import ru.otus.collectorio.service.impl.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@Import(CategoryServiceImpl.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private PermissionService permissionService;

    @Autowired
    private CategoryService categoryService;

    private final List<Category> categoryList = new ArrayList<>();

    @BeforeEach
    private void initData() {
        Category subCategory1 = new Category(2L, "Category 1.1", 1L, new ArrayList<>());
        Category subCategory2 = new Category(4L, "Category 1.2", 1L, new ArrayList<>());
        categoryList.add(new Category(1L, "Category 1", null, Arrays.asList(subCategory1, subCategory2)));
        categoryList.add(new Category(3L, "Category 2", null, new ArrayList<>()));
    }

    @Test
    public void getAllCategoryTest() {
        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<Category> expectedList = categoryService.getAllCategory();
    }

    @Test
    public void saveCategoryTest() {

    }

    @Test
    public void deleteByIdTest() {

    }

}
