package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.CollectibleItem;

public interface CollectibleItemRepository extends JpaRepository<CollectibleItem, Long> {
}
