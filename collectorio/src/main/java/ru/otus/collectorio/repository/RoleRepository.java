package ru.otus.collectorio.repository;

import ru.otus.collectorio.entity.role.Role;
import ru.otus.collectorio.entity.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName name);
}
