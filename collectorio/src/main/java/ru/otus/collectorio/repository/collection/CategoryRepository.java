package ru.otus.collectorio.repository.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.collection.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
