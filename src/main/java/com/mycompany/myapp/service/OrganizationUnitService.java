package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.OrganizationUnit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link OrganizationUnit}.
 */
public interface OrganizationUnitService {

    /**
     * Save a organizationUnit.
     *
     * @param organizationUnit the entity to save.
     * @return the persisted entity.
     */
    OrganizationUnit save(OrganizationUnit organizationUnit);

    /**
     * Get all the organizationUnits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrganizationUnit> findAll(Pageable pageable);

    /**
     * Get the "id" organizationUnit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrganizationUnit> findOne(Long id);

    /**
     * Delete the "id" organizationUnit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
