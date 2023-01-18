package ru.otus.collectorio.repository.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.collection.CollectionItem;

public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {

}
