package ru.otus.collectorio.service.impl;

import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.mapper.InfoCardMapper;
import ru.otus.collectorio.payload.request.infoCard.InfoCardExtRequest;
import ru.otus.collectorio.payload.request.infoCard.InfoCardRequest;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardResponse;
import ru.otus.collectorio.repository.InfoCardRepository;
import ru.otus.collectorio.service.InfoCardService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InfoCardServiceImpl implements InfoCardService {

    private final InfoCardRepository infoCardRepository;

    private final PermissionService permissionService;

    private final InfoCardMapper mapper;

    public InfoCardServiceImpl(InfoCardRepository repository,
                               PermissionService permissionService,
                               InfoCardMapper mapper) {
        this.infoCardRepository = repository;
        this.permissionService = permissionService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InfoCardResponse> getAllInCategory(Long id) {
        return infoCardRepository.findAllByCategory_Id(id)
                .stream().map(item -> mapper.toInfoCardResponse(item))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InfoCardExtResponse findById(Long id) {
        return mapper.toInfoCardExtResponse(infoCardRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException()));
    }

    @Override
    @Transactional
    public InfoCardResponse save(InfoCardRequest infoCardRequest) {
        InfoCard inputInfoCard = mapper.toInfoCard(infoCardRequest);
        InfoCard savedInfoCard;
        if (Objects.isNull(infoCardRequest.getId())) {
            savedInfoCard = infoCardRepository.save(inputInfoCard);
        } else {
            InfoCard category = infoCardRepository.findById(inputInfoCard.getId()).orElse(new InfoCard());
            category.setName(infoCardRequest.getName());
            savedInfoCard = infoCardRepository.save(category);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedInfoCard.getClass(), savedInfoCard.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toInfoCardResponse(savedInfoCard);
    }

    @Override
    @Transactional
    public InfoCardExtResponse save(InfoCardExtRequest infoCardExtRequest) {
        InfoCard inputInfoCard = mapper.toInfoCard(infoCardExtRequest);
        InfoCard savedInfoCard;
        if (Objects.isNull(infoCardExtRequest.getId())) {
            savedInfoCard = infoCardRepository.save(inputInfoCard);
        } else {
            InfoCard category = infoCardRepository.findById(inputInfoCard.getId()).orElse(new InfoCard());
            category.setName(infoCardExtRequest.getName());
            savedInfoCard = infoCardRepository.save(category);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedInfoCard.getClass(), savedInfoCard.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toInfoCardExtResponse(savedInfoCard);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        infoCardRepository.deleteById(id);
    }
}
