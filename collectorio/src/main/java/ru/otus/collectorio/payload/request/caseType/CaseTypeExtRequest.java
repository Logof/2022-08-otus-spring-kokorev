package ru.otus.collectorio.payload.request.caseType;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.category.CategoryRequest;

@Getter
@Setter
public class CaseTypeExtRequest extends CaseTypeRequest {
    private CategoryRequest category;
}
