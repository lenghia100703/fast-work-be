package com.fastwork.repositories;

import com.fastwork.entities.ConstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConstructionRepository extends JpaRepository<ConstructionEntity, Long> {
    @Query("SELECT c FROM ConstructionEntity c WHERE :id = c.owner.id")
    List<ConstructionEntity> findByOwnerId(@Param("id") Long id);
}
