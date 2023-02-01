package ru.otus.collectorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.entity.UserEntity;
import ru.otus.collectorio.payload.response.entity.UserInfo;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query(value = "insert into acl_sid (principal, sid) values (true, :#{#userInfo.username})", nativeQuery = true)
    void aclAddUser(@Param("userInfo") UserInfo userInfo);

    @Modifying
    @Query(value = "insert into acl_sid (principal, sid) values (false, :principal)", nativeQuery = true)
    void aclAddRole(@Param("principal") Role role);
}
