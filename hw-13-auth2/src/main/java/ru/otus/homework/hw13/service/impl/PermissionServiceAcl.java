package ru.otus.homework.hw13.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.otus.homework.hw13.entity.Book;
import ru.otus.homework.hw13.service.PermissionService;

@Service
@Transactional
public class PermissionServiceAcl implements PermissionService<Book> {

    @Autowired
    private MutableAclService aclService;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Override
    public void addPermissionForUser(Book targetObj, Permission permission, String username) {
        final Sid sid = new PrincipalSid(username);
        addPermissionForSid(targetObj, permission, sid);
    }

    private void addPermissionForSid(Book targetObj, Permission permission, Sid sid) {
        final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                final ObjectIdentity objectIdentity = new ObjectIdentityImpl(targetObj.getClass(), targetObj.getId());

                MutableAcl acl = null;
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
