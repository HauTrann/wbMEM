package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Equipment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Equipment}.
 */
public interface EquipmentService {

    /**
     * Save a equipment.
     *
     * @param equipment the entity to save.
     * @return the persisted entity.
     */
    Equipment save(Equipment equipment);

    /**
     * Get all the equipment.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Equipment> findAll(Pageable pageable);

    /**
     * Get the "id" equipment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Equipment> findOne(Long id);

    /**
     * Delete the "id" equipment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
