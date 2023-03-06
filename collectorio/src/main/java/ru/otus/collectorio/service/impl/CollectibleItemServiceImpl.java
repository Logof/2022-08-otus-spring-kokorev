package ru.otus.collectorio.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.mapper.CollectableItemMapper;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemExtRequest;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemExtResponse;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;
import ru.otus.collectorio.repository.CollectibleItemRepository;
import ru.otus.collectorio.service.CollectibleItemService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CollectibleItemServiceImpl implements CollectibleItemService {

    private final CollectibleItemRepository collectibleItemRepository;

    private final PermissionService permissionService;

    private final CollectableItemMapper mapper;

    public CollectibleItemServiceImpl(CollectibleItemRepository collectibleItemRepository,
                                      PermissionService permissionService,
                                      CollectableItemMapper mapper) {
        this.collectibleItemRepository = collectibleItemRepository;
        this.permissionService = permissionService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<CollectibleItemResponse> findAll() {
        return collectibleItemRepository.findAllByCreator(SecurityContextHolder.getContext().getAuthentication().getName())
                .stream().map(item -> mapper.toCollectibleItemResponse(item))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public CollectibleItemExtResponse findById(Long id) {
        return mapper.toCollectibleItemExtResponse(collectibleItemRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException()));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public CollectibleItemResponse save(CollectibleItemRequest collectibleItemRequest) {
        CollectibleItem inputCollectibleItem = mapper.toCollectibleItem(collectibleItemRequest);
        CollectibleItem savedCollectibleItem = collectibleItemRepository.save(inputCollectibleItem);

        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCollectibleItem.getClass(), savedCollectibleItem.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCollectibleItemResponse(savedCollectibleItem);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public CollectibleItemExtResponse save(CollectibleItemExtRequest collectibleItemExtRequest) {
        CollectibleItem inputCollectibleItem = mapper.toCollectibleItem(collectibleItemExtRequest);
        CollectibleItem savedCollectibleItem;
        if (Objects.isNull(collectibleItemExtRequest.getId())) {
            savedCollectibleItem = collectibleItemRepository.save(inputCollectibleItem);
        } else {
            CollectibleItem category = collectibleItemRepository.findById(inputCollectibleItem.getId()).orElse(new CollectibleItem());
            category.setName(collectibleItemExtRequest.getName());
            savedCollectibleItem = collectibleItemRepository.save(category);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCollectibleItem.getClass(), savedCollectibleItem.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCollectibleItemExtResponse(savedCollectibleItem);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void deleteById(Long id) {
        collectibleItemRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<CollectibleItemResponse> findCollectableItemByCollectionId(Long id) {
        return collectibleItemRepository.findAllByCollection_Id(id)
                .stream().map(item -> mapper.toCollectibleItemResponse(item)).collect(Collectors.toList());
    }

}
