package com.fastwork.repositories;

import com.fastwork.entities.UserEntity;
import com.fastwork.entities.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;


public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
    @Query("SELECT v FROM VerificationTokenEntity v WHERE :id = v.user.id")
    Optional<VerificationTokenEntity> findVerificationTokenByUserId(@Param("id") Long id);
}
