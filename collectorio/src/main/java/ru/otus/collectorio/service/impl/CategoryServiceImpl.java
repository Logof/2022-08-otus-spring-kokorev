package ru.otus.collectorio.service.impl;

import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.repository.CategoryRepository;
import ru.otus.collectorio.service.CategoryService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final PermissionService permissionService;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               PermissionService permissionService) {
        this.categoryRepository = categoryRepository;
        this.permissionService = permissionService;
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
    public Category saveCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCategory.getClass(), savedCategory.getId());
        permissionService.addPermissionByRole(objectIdentity, Role.ROLE_USER);
        return savedCategory;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
