package ru.otus.collectorio.payload.request.caseType;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.Request;

@Getter
@Setter
public class CaseTypeRequest implements Request {
    private Long id;

    private String name;
}
