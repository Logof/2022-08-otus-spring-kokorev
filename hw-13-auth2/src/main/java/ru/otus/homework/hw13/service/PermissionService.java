package ru.otus.homework.hw13.service;

import org.springframework.security.acls.model.Permission;

public interface PermissionService<T> {
    void addPermissionForUser(T targetObj, Permission permission, String username);

    void addPermissionForRole(T targetObj, Permission permission, String username);
}
