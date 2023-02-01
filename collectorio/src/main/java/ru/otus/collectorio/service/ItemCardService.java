package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.ItemCard;

import java.util.List;

public interface ItemCardService {
    List<ItemCard> getAllInCategory(Long id);

    ItemCard findById(Long id);

    ItemCard save(ItemCard itemCard);

    void deleteById(Long id);

    List<ItemCard> findAllByQuery(String name, Category category);
}
