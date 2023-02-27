package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.collectorio.entity.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("update Collection c set c.name = :name where c.id = :id")
    Collection setFixedNameById(@Param("id") Long id, @Param("name")String name);
}
