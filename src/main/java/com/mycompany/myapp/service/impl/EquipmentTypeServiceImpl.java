package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EquipmentTypeService;
import com.mycompany.myapp.domain.EquipmentType;
import com.mycompany.myapp.repository.EquipmentTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EquipmentType}.
 */
@Service
@Transactional
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

    private final Logger log = LoggerFactory.getLogger(EquipmentTypeServiceImpl.class);

    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentTypeServiceImpl(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    /**
     * Save a equipmentType.
     *
     * @param equipmentType the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EquipmentType save(EquipmentType equipmentType) {
        log.debug("Request to save EquipmentType : {}", equipmentType);
        return equipmentTypeRepository.save(equipmentType);
    }

    /**
     * Get all the equipmentTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EquipmentType> findAll(Pageable pageable) {
        log.debug("Request to get all EquipmentTypes");
        return equipmentTypeRepository.findAll(pageable);
    }

    /**
     * Get one equipmentType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EquipmentType> findOne(Long id) {
        log.debug("Request to get EquipmentType : {}", id);
        return equipmentTypeRepository.findById(id);
    }

    /**
     * Delete the equipmentType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EquipmentType : {}", id);
        equipmentTypeRepository.deleteById(id);
    }
}
