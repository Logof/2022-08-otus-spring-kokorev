package ru.otus.collectorio.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.exception.CategoryException;
import ru.otus.collectorio.mapper.CategoryMapper;
import ru.otus.collectorio.payload.request.category.CategoryExtRequest;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.category.CategoryExtResponse;
import ru.otus.collectorio.payload.response.category.CategoryHierarchicalResponse;
import ru.otus.collectorio.payload.response.category.CategoryResponse;
import ru.otus.collectorio.repository.CategoryRepository;
import ru.otus.collectorio.service.CategoryService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final PermissionService permissionService;

    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               PermissionService permissionService,
                               CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.permissionService = permissionService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryHierarchicalResponse> getAllCategoryHierarchy() {
        List<Category> result = categoryRepository.findAll();
        return mapper.toCategoryHierarchicalList(result);
    }

    @Override
    @Transactional
    public CategoryResponse save(CategoryRequest categoryRequest) throws CategoryException {
        Category savedCategory;
        if (Objects.isNull(categoryRequest.getId())) {
            savedCategory = categoryRepository.save(mapper.toCategory(categoryRequest));
        } else {
            Category category = categoryRepository.findById(categoryRequest.getId()).orElse(new Category());
            category.setName(categoryRequest.getName());
            savedCategory = categoryRepository.save(category);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCategory.getClass(), savedCategory.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCategoryResponse(savedCategory);
    }

    @Override
    public CategoryExtResponse save(CategoryExtRequest categoryRequest) {
        Category savedCategory;
        if (Objects.isNull(categoryRequest.getId())) {
            savedCategory = categoryRepository.save(mapper.toCategory(categoryRequest));
        } else {
            Category category = categoryRepository.findById(categoryRequest.getId()).orElse(new Category());
            category.setName(categoryRequest.getName());
            savedCategory = categoryRepository.save(category);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCategory.getClass(), savedCategory.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCategoryExtResponse(savedCategory);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
