package ru.otus.homework.hw13.service;

import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.GrantedAuthority;
import ru.otus.homework.hw13.entity.Book;

import java.util.Collection;

public interface PermissionService {
    void addPermissionForUser(Book targetObj, Permission permission, String username);

    void addPermissionForRole(Book targetObj, Permission permission, Collection<? extends GrantedAuthority> roles);
}
