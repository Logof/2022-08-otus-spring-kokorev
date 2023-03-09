package ru.otus.collectorio.payload.request.caseType;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.Request;
import ru.otus.collectorio.payload.request.category.CategoryRequest;

@Getter
@Setter
public class CaseTypeRequest implements Request {
    private Long id;

    private String name;

    private CategoryRequest category;
}
