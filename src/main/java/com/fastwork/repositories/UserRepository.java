package com.fastwork.repositories;

import com.fastwork.dtos.user.UserRoleDto;
import com.fastwork.entities.UserEntity;
import com.fastwork.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findById(Long id);

    @Query("""
            SELECT t FROM UserEntity t WHERE t.role <> com.fastwork.enums.Role.OWNER""")
    List<UserEntity> findAllUserByRole();

    @Query("""
    SELECT new com.fastwork.dtos.user.UserRoleDto(u.id, u.username, u.role)
    FROM UserEntity u
    UNION
    SELECT new com.fastwork.dtos.user.UserRoleDto(c.id, c.username, c.role)
    FROM ConstructionEntity c""")
    List<UserRoleDto> findAllUsernamesAndRoles();

    default Optional<?> findByIdAndRole(Long id, Role role, ConstructionRepository constructionRepository) {
        if (role == Role.HOST) {
            return constructionRepository.findById(id);
        } else {
            return findById(id);
        }
    }
}
