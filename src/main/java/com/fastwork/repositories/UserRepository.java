package com.fastwork.repositories;

import com.fastwork.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);

    @Query("""
            SELECT t FROM UserEntity t WHERE t.role <> com.fastwork.enums.Role.OWNER""")
    List<UserEntity> findAllUserByRole();
}
