package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DepartmentEmployeeService;
import com.mycompany.myapp.domain.DepartmentEmployee;
import com.mycompany.myapp.repository.DepartmentEmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DepartmentEmployee}.
 */
@Service
@Transactional
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {

    private final Logger log = LoggerFactory.getLogger(DepartmentEmployeeServiceImpl.class);

    private final DepartmentEmployeeRepository departmentEmployeeRepository;

    public DepartmentEmployeeServiceImpl(DepartmentEmployeeRepository departmentEmployeeRepository) {
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    /**
     * Save a departmentEmployee.
     *
     * @param departmentEmployee the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DepartmentEmployee save(DepartmentEmployee departmentEmployee) {
        log.debug("Request to save DepartmentEmployee : {}", departmentEmployee);
        return departmentEmployeeRepository.save(departmentEmployee);
    }

    /**
     * Get all the departmentEmployees.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentEmployee> findAll() {
        log.debug("Request to get all DepartmentEmployees");
        return departmentEmployeeRepository.findAll();
    }

    /**
     * Get one departmentEmployee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DepartmentEmployee> findOne(Long id) {
        log.debug("Request to get DepartmentEmployee : {}", id);
        return departmentEmployeeRepository.findById(id);
    }

    /**
     * Delete the departmentEmployee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DepartmentEmployee : {}", id);
        departmentEmployeeRepository.deleteById(id);
    }
}
