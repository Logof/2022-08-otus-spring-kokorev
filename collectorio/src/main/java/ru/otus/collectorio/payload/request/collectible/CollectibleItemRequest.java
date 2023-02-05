package ru.otus.collectorio.payload.request.collectible;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.request.caseType.CaseTypeWithoutCategoryRequest;
import ru.otus.collectorio.payload.request.item.InfoCardWithoutCategoryRequest;

import java.util.List;

@Getter
@Setter
public class CollectibleItemRequest {
    private Long id;

    private String name;

    private String condition;

    private String equipment;

    private Category category;

    private List<InfoCardWithoutCategoryRequest> infoCards;

    private String description;

    private String boxArtFront;

    private String boxArtBack;

    private String physicalMediaArt;

    private CaseTypeWithoutCategoryRequest caseType;
}
