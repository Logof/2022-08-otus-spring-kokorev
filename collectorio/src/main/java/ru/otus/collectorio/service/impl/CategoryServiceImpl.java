package ru.otus.collectorio.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.mapper.CategoryMapper;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.category.CategoryHierarchicalResponse;
import ru.otus.collectorio.payload.response.category.CategoryResponse;
import ru.otus.collectorio.repository.CategoryRepository;
import ru.otus.collectorio.service.CategoryService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;

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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category inputCategory = mapper.toCategory(categoryRequest);
        Category savedCategory = categoryRepository.save(inputCategory);

        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCategory.getClass(), savedCategory.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCategoryResponse(savedCategory);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
