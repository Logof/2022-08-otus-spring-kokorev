package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.response.item.InfoCardWithoutCategoryResponse;

import java.util.List;

public interface InfoCardService {
    List<InfoCard> getAllInCategoryWithCategory(Long id);

    List<InfoCardWithoutCategoryResponse> getAllInCategory(Long id);

    InfoCard findById(Long id);

    InfoCard save(InfoCard infoCard);

    void deleteById(Long id);

    List<InfoCard> findAllByQuery(String name, Category category);
}
