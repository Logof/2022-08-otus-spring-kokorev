package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.InfoCard;

import java.util.List;

public interface ItemCardRepository extends JpaRepository<InfoCard, Long> {

    List<InfoCard> findAllByCategory_Id(Long id);
    List<InfoCard> findAllByNameAndCategory(String name, Category category);

    List<InfoCard> findAllByNameAltAndCategory(String nameAlt, Category category);

}
