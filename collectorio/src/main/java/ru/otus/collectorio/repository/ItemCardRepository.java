package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.ItemCard;

import java.util.List;

public interface ItemCardRepository extends JpaRepository<ItemCard, Long> {

    List<ItemCard> findAllByCategory_Id(Long id);
    List<ItemCard> findAllByNameAndCategory(String name, Category category);

    List<ItemCard> findAllByNameAltAndCategory(String nameAlt, Category category);

}
