package ru.otus.collectorio.service.impl;

import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.CaseType;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.mapper.CaseTypeMapper;
import ru.otus.collectorio.payload.request.caseType.CaseTypeExtRequest;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeExtResponse;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;
import ru.otus.collectorio.repository.CaseTypeRepository;
import ru.otus.collectorio.service.CaseTypeService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;
import java.util.Objects;

@Service
public class CaseTypeServiceImpl implements CaseTypeService {

    private final CaseTypeRepository caseTypeRepository;

    private final PermissionService permissionService;

    private final CaseTypeMapper mapper;

    public CaseTypeServiceImpl(CaseTypeRepository caseTypeRepository,
                               PermissionService permissionService, CaseTypeMapper mapper) {
        this.caseTypeRepository = caseTypeRepository;
        this.permissionService = permissionService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CaseTypeResponse> getAllInCategory(Long categoryId) {
        return mapper.toCaseTypeResponseList(caseTypeRepository.findAllByCategory_Id(categoryId));
    }

    @Override
    @Transactional
    public CaseTypeResponse save(CaseTypeRequest caseTypeRequest) {
        CaseType savedCaseType;
        if (Objects.isNull(caseTypeRequest.getId())) {
            savedCaseType = caseTypeRepository.save(mapper.toCaseType(caseTypeRequest));
        } else {
            CaseType caseType = caseTypeRepository.findById(caseTypeRequest.getId()).orElse(new CaseType());
            caseType.setName(caseTypeRequest.getName());
            savedCaseType = caseTypeRepository.save(caseType);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCaseType.getClass(), savedCaseType.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCaseTypeResponse(savedCaseType);
    }

    @Override
    @Transactional
    public CaseTypeExtResponse save(CaseTypeExtRequest caseTypeExtRequest) {
        CaseType inputCaseType = mapper.toCaseType(caseTypeExtRequest);
        CaseType savedCaseType;
        if (Objects.isNull(inputCaseType.getId())) {
            savedCaseType = caseTypeRepository.save(inputCaseType);
        } else {
            CaseType caseType = caseTypeRepository.findById(caseTypeExtRequest.getId()).orElse(new CaseType());
            caseType.setName(inputCaseType.getName());
            caseType.setCategory(inputCaseType.getCategory());
            savedCaseType = caseTypeRepository.save(caseType);
        }
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCaseType.getClass(), savedCaseType.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCaseTypeExtResponse(savedCaseType);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
