package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EquipmentType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EquipmentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
}
