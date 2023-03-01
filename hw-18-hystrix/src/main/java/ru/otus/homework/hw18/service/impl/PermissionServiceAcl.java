package ru.otus.homework.hw18.service.impl;


import lombok.NonNull;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.otus.homework.hw18.entity.Book;
import ru.otus.homework.hw18.service.PermissionService;

@Service
public class PermissionServiceAcl implements PermissionService<Book> {

    private final MutableAclService aclService;

    private final PlatformTransactionManager transactionManager;

    public PermissionServiceAcl(MutableAclService aclService,
                                PlatformTransactionManager transactionManager) {
        this.aclService = aclService;
        this.transactionManager = transactionManager;
    }


    @Override
    public void addPermissionForUser(Book targetObj, Permission permission, String username) {
        final Sid sid = new PrincipalSid(username);
        addPermissionForSid(targetObj, permission, sid);
    }

    @Override
    public void addPermissionForRole(Book targetObj, Permission permission, String authority) {
        final Sid sid = new GrantedAuthoritySid(authority);
        addPermissionForSid(targetObj, permission, sid);
    }

    @Transactional
    private void addPermissionForSid(Book targetObj, Permission permission, Sid sid) {
        final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(@NonNull TransactionStatus status) {
                final ObjectIdentity objectIdentity = new ObjectIdentityImpl(targetObj.getClass(), targetObj.getId());

                MutableAcl acl;
                try {
                    acl = (MutableAcl) aclService.readAclById(objectIdentity);
                } catch (final NotFoundException nfe) {
                    acl = aclService.createAcl(objectIdentity);
                }

                acl.insertAce(acl.getEntries()
                    .size(), permission, sid, true);
                aclService.updateAcl(acl);
            }
        });
    }
}
