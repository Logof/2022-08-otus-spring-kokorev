package ru.otus.homework.hw16.security;

import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.model.Permission;

public class CustomRolesPermission extends AbstractPermission {
    public static final Permission ROLE_READER = new CustomRolesPermission(1, 'R');

    public static final Permission ROLE_EDITOR = new CustomRolesPermission(2, 'E');

    protected CustomRolesPermission(int mask, char code) {
        super(mask, code);
    }
}
