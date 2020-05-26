package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.OrganizationUnitService;
import com.mycompany.myapp.domain.OrganizationUnit;
import com.mycompany.myapp.repository.OrganizationUnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrganizationUnit}.
 */
@Service
@Transactional
public class OrganizationUnitServiceImpl implements OrganizationUnitService {

    private final Logger log = LoggerFactory.getLogger(OrganizationUnitServiceImpl.class);

    private final OrganizationUnitRepository organizationUnitRepository;

    public OrganizationUnitServiceImpl(OrganizationUnitRepository organizationUnitRepository) {
        this.organizationUnitRepository = organizationUnitRepository;
    }

    /**
     * Save a organizationUnit.
     *
     * @param organizationUnit the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrganizationUnit save(OrganizationUnit organizationUnit) {
        log.debug("Request to save OrganizationUnit : {}", organizationUnit);
        return organizationUnitRepository.save(organizationUnit);
    }

    /**
     * Get all the organizationUnits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrganizationUnit> findAll(Pageable pageable) {
        log.debug("Request to get all OrganizationUnits");
        return organizationUnitRepository.findAll(pageable);
    }

    /**
     * Get one organizationUnit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationUnit> findOne(Long id) {
        log.debug("Request to get OrganizationUnit : {}", id);
        return organizationUnitRepository.findById(id);
    }

    /**
     * Delete the organizationUnit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrganizationUnit : {}", id);
        organizationUnitRepository.deleteById(id);
    }
}
