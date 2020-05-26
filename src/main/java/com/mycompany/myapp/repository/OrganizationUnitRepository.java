package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrganizationUnit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrganizationUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnit, Long> {
}
