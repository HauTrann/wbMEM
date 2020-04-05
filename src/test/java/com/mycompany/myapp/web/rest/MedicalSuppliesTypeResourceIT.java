package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MedicalDeviceMngApp;
import com.mycompany.myapp.domain.MedicalSuppliesType;
import com.mycompany.myapp.repository.MedicalSuppliesTypeRepository;
import com.mycompany.myapp.service.MedicalSuppliesTypeService;

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
 * Integration tests for the {@link MedicalSuppliesTypeResource} REST controller.
 */
@SpringBootTest(classes = MedicalDeviceMngApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MedicalSuppliesTypeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private MedicalSuppliesTypeRepository medicalSuppliesTypeRepository;

    @Autowired
    private MedicalSuppliesTypeService medicalSuppliesTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalSuppliesTypeMockMvc;

    private MedicalSuppliesType medicalSuppliesType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalSuppliesType createEntity(EntityManager em) {
        MedicalSuppliesType medicalSuppliesType = new MedicalSuppliesType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return medicalSuppliesType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalSuppliesType createUpdatedEntity(EntityManager em) {
        MedicalSuppliesType medicalSuppliesType = new MedicalSuppliesType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        return medicalSuppliesType;
    }

    @BeforeEach
    public void initTest() {
        medicalSuppliesType = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalSuppliesType() throws Exception {
        int databaseSizeBeforeCreate = medicalSuppliesTypeRepository.findAll().size();

        // Create the MedicalSuppliesType
        restMedicalSuppliesTypeMockMvc.perform(post("/api/medical-supplies-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSuppliesType)))
            .andExpect(status().isCreated());

        // Validate the MedicalSuppliesType in the database
        List<MedicalSuppliesType> medicalSuppliesTypeList = medicalSuppliesTypeRepository.findAll();
        assertThat(medicalSuppliesTypeList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalSuppliesType testMedicalSuppliesType = medicalSuppliesTypeList.get(medicalSuppliesTypeList.size() - 1);
        assertThat(testMedicalSuppliesType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testMedicalSuppliesType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMedicalSuppliesType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMedicalSuppliesType.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMedicalSuppliesTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalSuppliesTypeRepository.findAll().size();

        // Create the MedicalSuppliesType with an existing ID
        medicalSuppliesType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalSuppliesTypeMockMvc.perform(post("/api/medical-supplies-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSuppliesType)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalSuppliesType in the database
        List<MedicalSuppliesType> medicalSuppliesTypeList = medicalSuppliesTypeRepository.findAll();
        assertThat(medicalSuppliesTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalSuppliesTypes() throws Exception {
        // Initialize the database
        medicalSuppliesTypeRepository.saveAndFlush(medicalSuppliesType);

        // Get all the medicalSuppliesTypeList
        restMedicalSuppliesTypeMockMvc.perform(get("/api/medical-supplies-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalSuppliesType.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getMedicalSuppliesType() throws Exception {
        // Initialize the database
        medicalSuppliesTypeRepository.saveAndFlush(medicalSuppliesType);

        // Get the medicalSuppliesType
        restMedicalSuppliesTypeMockMvc.perform(get("/api/medical-supplies-types/{id}", medicalSuppliesType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalSuppliesType.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingMedicalSuppliesType() throws Exception {
        // Get the medicalSuppliesType
        restMedicalSuppliesTypeMockMvc.perform(get("/api/medical-supplies-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalSuppliesType() throws Exception {
        // Initialize the database
        medicalSuppliesTypeService.save(medicalSuppliesType);

        int databaseSizeBeforeUpdate = medicalSuppliesTypeRepository.findAll().size();

        // Update the medicalSuppliesType
        MedicalSuppliesType updatedMedicalSuppliesType = medicalSuppliesTypeRepository.findById(medicalSuppliesType.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalSuppliesType are not directly saved in db
        em.detach(updatedMedicalSuppliesType);
        updatedMedicalSuppliesType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);

        restMedicalSuppliesTypeMockMvc.perform(put("/api/medical-supplies-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMedicalSuppliesType)))
            .andExpect(status().isOk());

        // Validate the MedicalSuppliesType in the database
        List<MedicalSuppliesType> medicalSuppliesTypeList = medicalSuppliesTypeRepository.findAll();
        assertThat(medicalSuppliesTypeList).hasSize(databaseSizeBeforeUpdate);
        MedicalSuppliesType testMedicalSuppliesType = medicalSuppliesTypeList.get(medicalSuppliesTypeList.size() - 1);
        assertThat(testMedicalSuppliesType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testMedicalSuppliesType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMedicalSuppliesType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMedicalSuppliesType.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalSuppliesType() throws Exception {
        int databaseSizeBeforeUpdate = medicalSuppliesTypeRepository.findAll().size();

        // Create the MedicalSuppliesType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalSuppliesTypeMockMvc.perform(put("/api/medical-supplies-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSuppliesType)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalSuppliesType in the database
        List<MedicalSuppliesType> medicalSuppliesTypeList = medicalSuppliesTypeRepository.findAll();
        assertThat(medicalSuppliesTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalSuppliesType() throws Exception {
        // Initialize the database
        medicalSuppliesTypeService.save(medicalSuppliesType);

        int databaseSizeBeforeDelete = medicalSuppliesTypeRepository.findAll().size();

        // Delete the medicalSuppliesType
        restMedicalSuppliesTypeMockMvc.perform(delete("/api/medical-supplies-types/{id}", medicalSuppliesType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalSuppliesType> medicalSuppliesTypeList = medicalSuppliesTypeRepository.findAll();
        assertThat(medicalSuppliesTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
