package ru.otus.homework.hw13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.hw13.entity.security.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, String> {
}
