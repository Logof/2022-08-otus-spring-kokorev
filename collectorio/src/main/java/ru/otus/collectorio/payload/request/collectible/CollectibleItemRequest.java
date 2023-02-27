package ru.otus.collectorio.payload.request.collectible;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.request.item.InfoCardRequest;

import java.util.List;

@Getter
@Setter
public class CollectibleItemRequest {

    private Long id;

    private String name;

    private String condition;

    private String equipment;

    private CategoryRequest category;

    private List<InfoCardRequest> infoCards;

    private CaseTypeRequest caseType;
}
