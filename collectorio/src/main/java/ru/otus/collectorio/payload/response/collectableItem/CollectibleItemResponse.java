package ru.otus.collectorio.payload.response.collectableItem;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;
import ru.otus.collectorio.payload.response.category.CategoryResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;

import java.util.List;

@Getter
@Setter
public class CollectibleItemResponse {
    private Long id;

    private String name;

    private String condition;

    private String equipment;

    private CategoryResponse category;

    private List<InfoCardExtResponse> infoCards;

    private CaseTypeResponse caseType;
}
