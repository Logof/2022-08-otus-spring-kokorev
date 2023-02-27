package ru.otus.collectorio.service;

import ru.otus.collectorio.payload.request.caseType.CaseTypeExtRequest;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeExtResponse;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;

import java.util.List;

public interface CaseTypeService {
    List<CaseTypeResponse> getAllInCategory(Long categoryId);

    CaseTypeResponse save(CaseTypeRequest caseType);

    CaseTypeExtResponse save(CaseTypeExtRequest caseTypeExtRequest);

    void deleteById(Long id);
}
