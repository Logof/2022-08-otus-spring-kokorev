package ru.otus.collectorio.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.mapper.CollectionEntityMapper;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;
import ru.otus.collectorio.payload.response.collection.CollectionResponse;
import ru.otus.collectorio.repository.CollectionRepository;
import ru.otus.collectorio.service.CollectionService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    private final PermissionService permissionService;

    private final CollectionEntityMapper mapper;

    public CollectionServiceImpl(CollectionRepository collectionRepository,
                                 PermissionService permissionService,
                                 CollectionEntityMapper mapper) {
        this.collectionRepository = collectionRepository;
        this.permissionService = permissionService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<CollectionResponse> findAll() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        return collectionRepository.findAllByCreator(user.getName())
                .stream().map(item->mapper.toCollectionResponse(item))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public CollectionResponse save(CollectionRequest collectionRequest) {
        Collection inputCollection = mapper.toCollection(collectionRequest);
        inputCollection.setCreator(SecurityContextHolder.getContext().getAuthentication().getName()
                .toLowerCase(Locale.ROOT));
        Collection savedCollection;
        if (Objects.isNull(collectionRequest.getId())) {
            savedCollection = collectionRepository.save(inputCollection);
        } else {
            Collection collection = collectionRepository.findById(inputCollection.getId()).orElse(new Collection());
            collection.setName(inputCollection.getName());
            savedCollection = collectionRepository.save(collection);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCollection.getClass(), savedCollection.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCollectionResponse(savedCollection);
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission(#id, 'ru.otus.collectorio.entity.Collection', 'DELETE')")
    public void deleteById(Long id) {
        collectionRepository.deleteById(id);
    }

}
