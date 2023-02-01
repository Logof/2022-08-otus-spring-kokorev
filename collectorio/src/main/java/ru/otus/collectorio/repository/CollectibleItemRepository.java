package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.CollectibleItem;

import java.util.List;

public interface CollectibleItemRepository extends JpaRepository<CollectibleItem, Long> {

    List<CollectibleItem> findAllByCollection_Id(Long id);
}
