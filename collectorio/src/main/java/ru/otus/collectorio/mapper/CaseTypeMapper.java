package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.collectorio.entity.CaseType;
import ru.otus.collectorio.payload.request.caseType.CaseTypeExtRequest;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeExtResponse;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CaseTypeMapper {

    @Mapping(target = "category", ignore = true)
    CaseType toCaseType(CaseTypeRequest categoryRequest);

    CaseType toCaseType(CaseTypeExtRequest categoryExtRequest);
    
    CaseTypeResponse toCaseTypeResponse(CaseType category);

    CaseTypeExtResponse toCaseTypeExtResponse(CaseType category);

    default List<CaseTypeResponse> toCaseTypeResponseList(List<CaseType> caseTypes) {
        return caseTypes.stream()
                .map((caseType -> toCaseTypeResponse(caseType)))
                .collect(Collectors.toList());
    }
}
