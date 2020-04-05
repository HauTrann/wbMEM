package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MedicalDeviceMngApp;
import com.mycompany.myapp.domain.DepartmentEmployee;
import com.mycompany.myapp.repository.DepartmentEmployeeRepository;
import com.mycompany.myapp.service.DepartmentEmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DepartmentEmployeeResource} REST controller.
 */
@SpringBootTest(classes = MedicalDeviceMngApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DepartmentEmployeeResourceIT {

    private static final Long DEFAULT_EMPLOYEE_ID = 1L;
    private static final Long UPDATED_EMPLOYEE_ID = 2L;

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepartmentEmployeeMockMvc;

    private DepartmentEmployee departmentEmployee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepartmentEmployee createEntity(EntityManager em) {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee()
            .employeeID(DEFAULT_EMPLOYEE_ID)
            .departmentID(DEFAULT_DEPARTMENT_ID)
            .status(DEFAULT_STATUS);
        return departmentEmployee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepartmentEmployee createUpdatedEntity(EntityManager em) {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee()
            .employeeID(UPDATED_EMPLOYEE_ID)
            .departmentID(UPDATED_DEPARTMENT_ID)
            .status(UPDATED_STATUS);
        return departmentEmployee;
    }

    @BeforeEach
    public void initTest() {
        departmentEmployee = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepartmentEmployee() throws Exception {
        int databaseSizeBeforeCreate = departmentEmployeeRepository.findAll().size();

        // Create the DepartmentEmployee
        restDepartmentEmployeeMockMvc.perform(post("/api/department-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departmentEmployee)))
            .andExpect(status().isCreated());

        // Validate the DepartmentEmployee in the database
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeRepository.findAll();
        assertThat(departmentEmployeeList).hasSize(databaseSizeBeforeCreate + 1);
        DepartmentEmployee testDepartmentEmployee = departmentEmployeeList.get(departmentEmployeeList.size() - 1);
        assertThat(testDepartmentEmployee.getEmployeeID()).isEqualTo(DEFAULT_EMPLOYEE_ID);
        assertThat(testDepartmentEmployee.getDepartmentID()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testDepartmentEmployee.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createDepartmentEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departmentEmployeeRepository.findAll().size();

        // Create the DepartmentEmployee with an existing ID
        departmentEmployee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentEmployeeMockMvc.perform(post("/api/department-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departmentEmployee)))
            .andExpect(status().isBadRequest());

        // Validate the DepartmentEmployee in the database
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeRepository.findAll();
        assertThat(departmentEmployeeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDepartmentEmployees() throws Exception {
        // Initialize the database
        departmentEmployeeRepository.saveAndFlush(departmentEmployee);

        // Get all the departmentEmployeeList
        restDepartmentEmployeeMockMvc.perform(get("/api/department-employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departmentEmployee.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeID").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentID").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getDepartmentEmployee() throws Exception {
        // Initialize the database
        departmentEmployeeRepository.saveAndFlush(departmentEmployee);

        // Get the departmentEmployee
        restDepartmentEmployeeMockMvc.perform(get("/api/department-employees/{id}", departmentEmployee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(departmentEmployee.getId().intValue()))
            .andExpect(jsonPath("$.employeeID").value(DEFAULT_EMPLOYEE_ID.intValue()))
            .andExpect(jsonPath("$.departmentID").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingDepartmentEmployee() throws Exception {
        // Get the departmentEmployee
        restDepartmentEmployeeMockMvc.perform(get("/api/department-employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartmentEmployee() throws Exception {
        // Initialize the database
        departmentEmployeeService.save(departmentEmployee);

        int databaseSizeBeforeUpdate = departmentEmployeeRepository.findAll().size();

        // Update the departmentEmployee
        DepartmentEmployee updatedDepartmentEmployee = departmentEmployeeRepository.findById(departmentEmployee.getId()).get();
        // Disconnect from session so that the updates on updatedDepartmentEmployee are not directly saved in db
        em.detach(updatedDepartmentEmployee);
        updatedDepartmentEmployee
            .employeeID(UPDATED_EMPLOYEE_ID)
            .departmentID(UPDATED_DEPARTMENT_ID)
            .status(UPDATED_STATUS);

        restDepartmentEmployeeMockMvc.perform(put("/api/department-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDepartmentEmployee)))
            .andExpect(status().isOk());

        // Validate the DepartmentEmployee in the database
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeRepository.findAll();
        assertThat(departmentEmployeeList).hasSize(databaseSizeBeforeUpdate);
        DepartmentEmployee testDepartmentEmployee = departmentEmployeeList.get(departmentEmployeeList.size() - 1);
        assertThat(testDepartmentEmployee.getEmployeeID()).isEqualTo(UPDATED_EMPLOYEE_ID);
        assertThat(testDepartmentEmployee.getDepartmentID()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testDepartmentEmployee.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingDepartmentEmployee() throws Exception {
        int databaseSizeBeforeUpdate = departmentEmployeeRepository.findAll().size();

        // Create the DepartmentEmployee

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartmentEmployeeMockMvc.perform(put("/api/department-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departmentEmployee)))
            .andExpect(status().isBadRequest());

        // Validate the DepartmentEmployee in the database
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeRepository.findAll();
        assertThat(departmentEmployeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepartmentEmployee() throws Exception {
        // Initialize the database
        departmentEmployeeService.save(departmentEmployee);

        int databaseSizeBeforeDelete = departmentEmployeeRepository.findAll().size();

        // Delete the departmentEmployee
        restDepartmentEmployeeMockMvc.perform(delete("/api/department-employees/{id}", departmentEmployee.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeRepository.findAll();
        assertThat(departmentEmployeeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
