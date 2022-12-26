package ru.otus.homework.hw13.service;

import org.springframework.security.acls.model.Permission;
import ru.otus.homework.hw13.entity.Book;

public interface PermissionService {
    void addPermissionForUser(Book targetObj, Permission permission, String username);

}
