package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.DepartmentEmployee;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DepartmentEmployee}.
 */
public interface DepartmentEmployeeService {

    /**
     * Save a departmentEmployee.
     *
     * @param departmentEmployee the entity to save.
     * @return the persisted entity.
     */
    DepartmentEmployee save(DepartmentEmployee departmentEmployee);

    /**
     * Get all the departmentEmployees.
     *
     * @return the list of entities.
     */
    List<DepartmentEmployee> findAll();

    /**
     * Get the "id" departmentEmployee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepartmentEmployee> findOne(Long id);

    /**
     * Delete the "id" departmentEmployee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
