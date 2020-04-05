package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.MedicalSupplies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MedicalSupplies}.
 */
public interface MedicalSuppliesService {

    /**
     * Save a medicalSupplies.
     *
     * @param medicalSupplies the entity to save.
     * @return the persisted entity.
     */
    MedicalSupplies save(MedicalSupplies medicalSupplies);

    /**
     * Get all the medicalSupplies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MedicalSupplies> findAll(Pageable pageable);

    /**
     * Get the "id" medicalSupplies.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedicalSupplies> findOne(Long id);

    /**
     * Delete the "id" medicalSupplies.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
