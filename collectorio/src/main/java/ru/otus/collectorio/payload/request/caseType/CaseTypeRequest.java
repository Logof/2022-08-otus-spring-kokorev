package ru.otus.collectorio.payload.request.caseType;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Category;

@Getter
@Setter
public class CaseTypeRequest {
    private Long id;

    private Category category;

    private String name;
}
