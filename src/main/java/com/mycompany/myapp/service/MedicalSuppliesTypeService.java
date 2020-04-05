package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.MedicalSuppliesType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MedicalSuppliesType}.
 */
public interface MedicalSuppliesTypeService {

    /**
     * Save a medicalSuppliesType.
     *
     * @param medicalSuppliesType the entity to save.
     * @return the persisted entity.
     */
    MedicalSuppliesType save(MedicalSuppliesType medicalSuppliesType);

    /**
     * Get all the medicalSuppliesTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MedicalSuppliesType> findAll(Pageable pageable);

    /**
     * Get the "id" medicalSuppliesType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedicalSuppliesType> findOne(Long id);

    /**
     * Delete the "id" medicalSuppliesType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
