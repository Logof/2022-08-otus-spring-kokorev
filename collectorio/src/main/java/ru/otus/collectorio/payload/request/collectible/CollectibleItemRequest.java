package ru.otus.collectorio.payload.request.collectible;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.request.item.InfoCardWithoutCategoryRequest;

import java.util.List;

@Getter
@Setter
public class CollectibleItemRequest {

    private String name;

    private String condition;

    private String equipment;

    private CategoryRequest category;

    private List<InfoCardWithoutCategoryRequest> infoCards;

    private String description;

    private String boxArtFront;

    private String boxArtBack;

    private String physicalMediaArt;

    private CaseTypeRequest caseType;
}
