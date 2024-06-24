package com.fastwork.repositories;

import com.fastwork.entities.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
}
