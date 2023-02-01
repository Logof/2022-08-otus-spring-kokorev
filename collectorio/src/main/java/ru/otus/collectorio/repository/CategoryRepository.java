package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
