package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MedicalSuppliesTypeService;
import com.mycompany.myapp.domain.MedicalSuppliesType;
import com.mycompany.myapp.repository.MedicalSuppliesTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalSuppliesType}.
 */
@Service
@Transactional
public class MedicalSuppliesTypeServiceImpl implements MedicalSuppliesTypeService {

    private final Logger log = LoggerFactory.getLogger(MedicalSuppliesTypeServiceImpl.class);

    private final MedicalSuppliesTypeRepository medicalSuppliesTypeRepository;

    public MedicalSuppliesTypeServiceImpl(MedicalSuppliesTypeRepository medicalSuppliesTypeRepository) {
        this.medicalSuppliesTypeRepository = medicalSuppliesTypeRepository;
    }

    /**
     * Save a medicalSuppliesType.
     *
     * @param medicalSuppliesType the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MedicalSuppliesType save(MedicalSuppliesType medicalSuppliesType) {
        log.debug("Request to save MedicalSuppliesType : {}", medicalSuppliesType);
        return medicalSuppliesTypeRepository.save(medicalSuppliesType);
    }

    /**
     * Get all the medicalSuppliesTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MedicalSuppliesType> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalSuppliesTypes");
        return medicalSuppliesTypeRepository.findAll(pageable);
    }

    /**
     * Get one medicalSuppliesType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MedicalSuppliesType> findOne(Long id) {
        log.debug("Request to get MedicalSuppliesType : {}", id);
        return medicalSuppliesTypeRepository.findById(id);
    }

    /**
     * Delete the medicalSuppliesType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicalSuppliesType : {}", id);
        medicalSuppliesTypeRepository.deleteById(id);
    }
}
