package ru.otus.collectorio.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.log.LogMessage;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.ObjectIdentityRetrievalStrategyImpl;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.domain.SidRetrievalStrategyImpl;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Slf4j
@Component
public class MySecurityFilter {

    private final AclService aclService;

    private ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy = new ObjectIdentityRetrievalStrategyImpl();

    private SidRetrievalStrategy sidRetrievalStrategy = new SidRetrievalStrategyImpl();

    private PermissionFactory permissionFactory = new DefaultPermissionFactory();

    public MySecurityFilter(AclService aclService) {
        this.aclService = aclService;
    }

    public boolean filter(Authentication authentication, List<Object> domain, Object permission) {
        log.info("It's work");
        for (Object domainObject : domain) {
            ObjectIdentity objectIdentity = this.objectIdentityRetrievalStrategy.getObjectIdentity(domainObject);
            if (!checkPermission(authentication, objectIdentity, permission)) {
                domain.remove(domainObject);
            }
        }
        return true;
    }

    private boolean checkPermission(Authentication authentication, ObjectIdentity oid, Object permission) {
        // Obtain the SIDs applicable to the principal
        List<Sid> sids = this.sidRetrievalStrategy.getSids(authentication);
        List<Permission> requiredPermission = resolvePermission(permission);
        log.info("{}", LogMessage.of(() -> "Checking permission '" + permission + "' for object '" + oid + "'"));
        try {
            // Lookup only ACLs for SIDs we're interested in
            Acl acl = this.aclService.readAclById(oid, sids);
            if (acl.isGranted(requiredPermission, sids, false)) {
                log.info("Access is granted");
                return true;
            }
            log.info("Returning false - ACLs returned, but insufficient permissions for this principal");
        }
        catch (NotFoundException nfe) {
            log.info("Returning false - no ACLs apply for this principal");
        }
        return false;
    }

    List<Permission> resolvePermission(Object permission) {
        if (permission instanceof Integer) {
            return Arrays.asList(this.permissionFactory.buildFromMask((Integer) permission));
        }
        if (permission instanceof Permission) {
            return Arrays.asList((Permission) permission);
        }
        if (permission instanceof Permission[]) {
            return Arrays.asList((Permission[]) permission);
        }
        if (permission instanceof String) {
            String permString = (String) permission;
            Permission p = buildPermission(permString);
            if (p != null) {
                return Arrays.asList(p);
            }
        }
        throw new IllegalArgumentException("Unsupported permission: " + permission);
    }

    private Permission buildPermission(String permString) {
        try {
            return permissionFactory.buildFromName(permString);
        }
        catch (IllegalArgumentException e) {
            return permissionFactory.buildFromName(permString.toUpperCase(Locale.ENGLISH));
        }
    }
}
