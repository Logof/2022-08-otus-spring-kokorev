package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
