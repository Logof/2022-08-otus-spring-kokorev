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
import java.util.List;

@SpringBootTest
@Import(CategoryServiceImpl.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryExtRepository;

    @MockBean
    private PermissionService permissionService;

    @Autowired
    private CategoryService categoryService;

    private final List<Category> categoryList = new ArrayList<>();

    @BeforeEach
    private void initData() {

    }

    @Test
    public void getAllCategoryTest() {

    }

    @Test
    public void saveCategoryTest() {

    }

    @Test
    public void deleteByIdTest() {

    }

}
