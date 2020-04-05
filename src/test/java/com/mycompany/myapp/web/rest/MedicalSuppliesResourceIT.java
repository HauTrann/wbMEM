package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MedicalDeviceMngApp;
import com.mycompany.myapp.domain.MedicalSupplies;
import com.mycompany.myapp.repository.MedicalSuppliesRepository;
import com.mycompany.myapp.service.MedicalSuppliesService;

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
 * Integration tests for the {@link MedicalSuppliesResource} REST controller.
 */
@SpringBootTest(classes = MedicalDeviceMngApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MedicalSuppliesResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_MEDICAL_SUPPLIES_TYPE_ID = 1L;
    private static final Long UPDATED_MEDICAL_SUPPLIES_TYPE_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private MedicalSuppliesRepository medicalSuppliesRepository;

    @Autowired
    private MedicalSuppliesService medicalSuppliesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalSuppliesMockMvc;

    private MedicalSupplies medicalSupplies;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalSupplies createEntity(EntityManager em) {
        MedicalSupplies medicalSupplies = new MedicalSupplies()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .medicalSuppliesTypeID(DEFAULT_MEDICAL_SUPPLIES_TYPE_ID)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return medicalSupplies;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalSupplies createUpdatedEntity(EntityManager em) {
        MedicalSupplies medicalSupplies = new MedicalSupplies()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .medicalSuppliesTypeID(UPDATED_MEDICAL_SUPPLIES_TYPE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        return medicalSupplies;
    }

    @BeforeEach
    public void initTest() {
        medicalSupplies = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalSupplies() throws Exception {
        int databaseSizeBeforeCreate = medicalSuppliesRepository.findAll().size();

        // Create the MedicalSupplies
        restMedicalSuppliesMockMvc.perform(post("/api/medical-supplies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSupplies)))
            .andExpect(status().isCreated());

        // Validate the MedicalSupplies in the database
        List<MedicalSupplies> medicalSuppliesList = medicalSuppliesRepository.findAll();
        assertThat(medicalSuppliesList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalSupplies testMedicalSupplies = medicalSuppliesList.get(medicalSuppliesList.size() - 1);
        assertThat(testMedicalSupplies.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testMedicalSupplies.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMedicalSupplies.getMedicalSuppliesTypeID()).isEqualTo(DEFAULT_MEDICAL_SUPPLIES_TYPE_ID);
        assertThat(testMedicalSupplies.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMedicalSupplies.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMedicalSuppliesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalSuppliesRepository.findAll().size();

        // Create the MedicalSupplies with an existing ID
        medicalSupplies.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalSuppliesMockMvc.perform(post("/api/medical-supplies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSupplies)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalSupplies in the database
        List<MedicalSupplies> medicalSuppliesList = medicalSuppliesRepository.findAll();
        assertThat(medicalSuppliesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalSupplies() throws Exception {
        // Initialize the database
        medicalSuppliesRepository.saveAndFlush(medicalSupplies);

        // Get all the medicalSuppliesList
        restMedicalSuppliesMockMvc.perform(get("/api/medical-supplies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalSupplies.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].medicalSuppliesTypeID").value(hasItem(DEFAULT_MEDICAL_SUPPLIES_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getMedicalSupplies() throws Exception {
        // Initialize the database
        medicalSuppliesRepository.saveAndFlush(medicalSupplies);

        // Get the medicalSupplies
        restMedicalSuppliesMockMvc.perform(get("/api/medical-supplies/{id}", medicalSupplies.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalSupplies.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.medicalSuppliesTypeID").value(DEFAULT_MEDICAL_SUPPLIES_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingMedicalSupplies() throws Exception {
        // Get the medicalSupplies
        restMedicalSuppliesMockMvc.perform(get("/api/medical-supplies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalSupplies() throws Exception {
        // Initialize the database
        medicalSuppliesService.save(medicalSupplies);

        int databaseSizeBeforeUpdate = medicalSuppliesRepository.findAll().size();

        // Update the medicalSupplies
        MedicalSupplies updatedMedicalSupplies = medicalSuppliesRepository.findById(medicalSupplies.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalSupplies are not directly saved in db
        em.detach(updatedMedicalSupplies);
        updatedMedicalSupplies
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .medicalSuppliesTypeID(UPDATED_MEDICAL_SUPPLIES_TYPE_ID)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);

        restMedicalSuppliesMockMvc.perform(put("/api/medical-supplies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMedicalSupplies)))
            .andExpect(status().isOk());

        // Validate the MedicalSupplies in the database
        List<MedicalSupplies> medicalSuppliesList = medicalSuppliesRepository.findAll();
        assertThat(medicalSuppliesList).hasSize(databaseSizeBeforeUpdate);
        MedicalSupplies testMedicalSupplies = medicalSuppliesList.get(medicalSuppliesList.size() - 1);
        assertThat(testMedicalSupplies.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testMedicalSupplies.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMedicalSupplies.getMedicalSuppliesTypeID()).isEqualTo(UPDATED_MEDICAL_SUPPLIES_TYPE_ID);
        assertThat(testMedicalSupplies.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMedicalSupplies.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalSupplies() throws Exception {
        int databaseSizeBeforeUpdate = medicalSuppliesRepository.findAll().size();

        // Create the MedicalSupplies

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalSuppliesMockMvc.perform(put("/api/medical-supplies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSupplies)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalSupplies in the database
        List<MedicalSupplies> medicalSuppliesList = medicalSuppliesRepository.findAll();
        assertThat(medicalSuppliesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalSupplies() throws Exception {
        // Initialize the database
        medicalSuppliesService.save(medicalSupplies);

        int databaseSizeBeforeDelete = medicalSuppliesRepository.findAll().size();

        // Delete the medicalSupplies
        restMedicalSuppliesMockMvc.perform(delete("/api/medical-supplies/{id}", medicalSupplies.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalSupplies> medicalSuppliesList = medicalSuppliesRepository.findAll();
        assertThat(medicalSuppliesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
