package ru.otus.collectorio.payload.request.caseType;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.Request;
import ru.otus.collectorio.payload.request.category.CategoryRequest;

@Getter
@Setter
public class CaseTypeExtRequest extends CaseTypeRequest implements Request {
    private CategoryRequest category;
}
