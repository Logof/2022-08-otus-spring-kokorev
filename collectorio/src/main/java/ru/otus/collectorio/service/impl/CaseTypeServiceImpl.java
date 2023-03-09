package ru.otus.collectorio.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.CaseType;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.mapper.CaseTypeMapper;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeExtResponse;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;
import ru.otus.collectorio.repository.CaseTypeRepository;
import ru.otus.collectorio.service.CaseTypeService;
import ru.otus.collectorio.service.PermissionService;

import java.util.List;

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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CaseTypeExtResponse save(CaseTypeRequest caseTypeRequest) {
        CaseType savedCaseType = caseTypeRepository.save(mapper.toCaseType(caseTypeRequest));
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedCaseType.getClass(), savedCaseType.getId());
        permissionService.addPermission(objectIdentity, Role.ROLE_USER);
        return mapper.toCaseTypeExtResponse(savedCaseType);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteById(Long id) {
        caseTypeRepository.deleteById(id);
    }
}
