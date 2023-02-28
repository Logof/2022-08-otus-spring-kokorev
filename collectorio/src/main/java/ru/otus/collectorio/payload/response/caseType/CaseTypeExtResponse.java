package ru.otus.collectorio.payload.response.caseType;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

@Getter
@Setter
public class CaseTypeExtResponse extends CaseTypeResponse implements Response {
    private CategoryResponse category;
}
