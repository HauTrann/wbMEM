package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.EquipmentType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link EquipmentType}.
 */
public interface EquipmentTypeService {

    /**
     * Save a equipmentType.
     *
     * @param equipmentType the entity to save.
     * @return the persisted entity.
     */
    EquipmentType save(EquipmentType equipmentType);

    /**
     * Get all the equipmentTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EquipmentType> findAll(Pageable pageable);

    /**
     * Get the "id" equipmentType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EquipmentType> findOne(Long id);

    /**
     * Delete the "id" equipmentType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
