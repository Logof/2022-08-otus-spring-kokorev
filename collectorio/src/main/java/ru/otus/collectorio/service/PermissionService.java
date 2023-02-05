package ru.otus.collectorio.service;

import org.springframework.security.acls.model.ObjectIdentity;
import ru.otus.collectorio.entity.Role;

public interface PermissionService {

    void addPermission(ObjectIdentity objectIdentity, Role role);
}
