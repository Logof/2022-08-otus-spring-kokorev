package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.CaseType;

import java.util.List;

public interface CaseTypeRepository extends JpaRepository<CaseType, Long> {
    List<CaseType> findAllByCategory_Id(Long id);
}
