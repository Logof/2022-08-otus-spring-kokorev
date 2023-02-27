package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.response.item.InfoCardWithoutCategoryResponse;

@Mapper
public interface ItemCardMapper {
    InfoCardWithoutCategoryResponse toWithoutCategoryResponse(InfoCard infoCard);
}
