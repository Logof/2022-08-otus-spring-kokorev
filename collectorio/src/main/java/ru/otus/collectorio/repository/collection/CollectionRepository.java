package ru.otus.collectorio.repository.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.collection.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
