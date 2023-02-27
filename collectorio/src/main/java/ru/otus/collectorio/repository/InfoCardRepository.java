package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.InfoCard;

import java.util.List;

public interface InfoCardRepository extends JpaRepository<InfoCard, Long> {

    List<InfoCard> findAllByCategory_Id(Long id);

}
