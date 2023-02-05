package ru.otus.collectorio.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.mapper.CategoryRequestMapper;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.repository.CategoryRepository;
import ru.otus.collectorio.service.CategoryService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final PermissionService permissionService;

    private final CategoryRequestMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               PermissionService permissionService,
                               CategoryRequestMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.permissionService = permissionService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .filter(category -> category.isChildren() == false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Category saveCategory(CategoryRequest categoryRequest) {
        Category savedCategory = categoryRepository.save(mapper.toCategory(categoryRequest));
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCategory.getClass(), savedCategory.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return savedCategory;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
