package ru.otus.collectorio.payload.response.caseType;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;

@Getter
@Setter
public class CaseTypeResponse implements Response {
    private Long id;
    private String name;
}
