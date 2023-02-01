package ru.otus.collectorio.service.impl;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.CumulativePermission;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
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
    public void addPermissionByRole(ObjectIdentity objectIdentity, Role role) {

        MutableAcl acl = mutableAclService.createAcl(objectIdentity);

        if (Role.ROLE_USER == role) {
            Sid owner = new PrincipalSid(Role.ROLE_USER.name());
            acl.setOwner(owner);

            CumulativePermission cumulativePermissionForOwner = new CumulativePermission();
            cumulativePermissionForOwner.set(BasePermission.READ);
            cumulativePermissionForOwner.set(BasePermission.WRITE);
            acl.insertAce(acl.getEntries().size(), cumulativePermissionForOwner, owner, true);
        }


        Sid admin = new PrincipalSid(Role.ROLE_ADMIN.name());
        CumulativePermission cumulativePermissionForAdmin = new CumulativePermission();
        cumulativePermissionForAdmin.set(BasePermission.ADMINISTRATION);
        cumulativePermissionForAdmin.set(BasePermission.CREATE);
        cumulativePermissionForAdmin.set(BasePermission.DELETE);
        cumulativePermissionForAdmin.set(BasePermission.READ);
        cumulativePermissionForAdmin.set(BasePermission.WRITE);
        acl.insertAce(acl.getEntries().size(), cumulativePermissionForAdmin, admin, true);

    }
}
