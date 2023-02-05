package ru.otus.collectorio.service.impl;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.CumulativePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final MutableAclService mutableAclService;



    public PermissionServiceImpl(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Override
    public void addPermission(ObjectIdentity objectIdentity, Role role) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();

        // создать SID-ы для владельца и пользователя
        final Sid owner = new PrincipalSid(user);
        final Sid admin = new GrantedAuthoritySid(Role.ROLE_ADMIN.name());

        // создать пустой ACL
        MutableAcl acl = mutableAclService.createAcl(objectIdentity);

        // определить владельца сущности и права пользователей
        acl.setOwner(owner);

        CumulativePermission cumulativePermissionForOwner = new CumulativePermission();
        cumulativePermissionForOwner.set(BasePermission.READ);
        cumulativePermissionForOwner.set(BasePermission.WRITE);

        acl.insertAce(acl.getEntries().size(), cumulativePermissionForOwner, owner, true);
        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, admin, true);

        // обновить ACL в БД
        mutableAclService.updateAcl(acl);
/*
        Sid adminGroup = new PrincipalSid(Role.ROLE_ADMIN.name());
        CumulativePermission cumulativePermissionForAdmin = new CumulativePermission();
        cumulativePermissionForAdmin.set(BasePermission.ADMINISTRATION);
        cumulativePermissionForAdmin.set(BasePermission.CREATE);
        cumulativePermissionForAdmin.set(BasePermission.DELETE);
        cumulativePermissionForAdmin.set(BasePermission.READ);
        cumulativePermissionForAdmin.set(BasePermission.WRITE);
        acl.insertAce(acl.getEntries().size(), cumulativePermissionForAdmin, adminGroup, true);*/

    }
}
