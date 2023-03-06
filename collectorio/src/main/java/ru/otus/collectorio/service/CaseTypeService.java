package ru.otus.collectorio.service;

import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeExtResponse;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;

import java.util.List;

public interface CaseTypeService {
    List<CaseTypeResponse> getAllInCategory(Long categoryId);

    CaseTypeExtResponse save(CaseTypeRequest caseTypeExtRequest);

    void deleteById(Long id);
}
