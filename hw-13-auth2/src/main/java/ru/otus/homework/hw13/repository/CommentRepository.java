package ru.otus.homework.hw13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import ru.otus.homework.hw13.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Comment> findAllByBook_Id(long isbn);
}
