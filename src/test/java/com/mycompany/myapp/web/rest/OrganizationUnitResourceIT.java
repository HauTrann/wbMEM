package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MedicalDeviceMngApp;
import com.mycompany.myapp.domain.OrganizationUnit;
import com.mycompany.myapp.repository.OrganizationUnitRepository;
import com.mycompany.myapp.service.OrganizationUnitService;

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
 * Integration tests for the {@link OrganizationUnitResource} REST controller.
 */
@SpringBootTest(classes = MedicalDeviceMngApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrganizationUnitResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private OrganizationUnitRepository organizationUnitRepository;

    @Autowired
    private OrganizationUnitService organizationUnitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganizationUnitMockMvc;

    private OrganizationUnit organizationUnit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganizationUnit createEntity(EntityManager em) {
        OrganizationUnit organizationUnit = new OrganizationUnit()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return organizationUnit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganizationUnit createUpdatedEntity(EntityManager em) {
        OrganizationUnit organizationUnit = new OrganizationUnit()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        return organizationUnit;
    }

    @BeforeEach
    public void initTest() {
        organizationUnit = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganizationUnit() throws Exception {
        int databaseSizeBeforeCreate = organizationUnitRepository.findAll().size();

        // Create the OrganizationUnit
        restOrganizationUnitMockMvc.perform(post("/api/organization-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizationUnit)))
            .andExpect(status().isCreated());

        // Validate the OrganizationUnit in the database
        List<OrganizationUnit> organizationUnitList = organizationUnitRepository.findAll();
        assertThat(organizationUnitList).hasSize(databaseSizeBeforeCreate + 1);
        OrganizationUnit testOrganizationUnit = organizationUnitList.get(organizationUnitList.size() - 1);
        assertThat(testOrganizationUnit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testOrganizationUnit.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrganizationUnit.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrganizationUnit.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createOrganizationUnitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizationUnitRepository.findAll().size();

        // Create the OrganizationUnit with an existing ID
        organizationUnit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizationUnitMockMvc.perform(post("/api/organization-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizationUnit)))
            .andExpect(status().isBadRequest());

        // Validate the OrganizationUnit in the database
        List<OrganizationUnit> organizationUnitList = organizationUnitRepository.findAll();
        assertThat(organizationUnitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrganizationUnits() throws Exception {
        // Initialize the database
        organizationUnitRepository.saveAndFlush(organizationUnit);

        // Get all the organizationUnitList
        restOrganizationUnitMockMvc.perform(get("/api/organization-units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organizationUnit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getOrganizationUnit() throws Exception {
        // Initialize the database
        organizationUnitRepository.saveAndFlush(organizationUnit);

        // Get the organizationUnit
        restOrganizationUnitMockMvc.perform(get("/api/organization-units/{id}", organizationUnit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organizationUnit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingOrganizationUnit() throws Exception {
        // Get the organizationUnit
        restOrganizationUnitMockMvc.perform(get("/api/organization-units/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganizationUnit() throws Exception {
        // Initialize the database
        organizationUnitService.save(organizationUnit);

        int databaseSizeBeforeUpdate = organizationUnitRepository.findAll().size();

        // Update the organizationUnit
        OrganizationUnit updatedOrganizationUnit = organizationUnitRepository.findById(organizationUnit.getId()).get();
        // Disconnect from session so that the updates on updatedOrganizationUnit are not directly saved in db
        em.detach(updatedOrganizationUnit);
        updatedOrganizationUnit
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);

        restOrganizationUnitMockMvc.perform(put("/api/organization-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrganizationUnit)))
            .andExpect(status().isOk());

        // Validate the OrganizationUnit in the database
        List<OrganizationUnit> organizationUnitList = organizationUnitRepository.findAll();
        assertThat(organizationUnitList).hasSize(databaseSizeBeforeUpdate);
        OrganizationUnit testOrganizationUnit = organizationUnitList.get(organizationUnitList.size() - 1);
        assertThat(testOrganizationUnit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testOrganizationUnit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrganizationUnit.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrganizationUnit.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganizationUnit() throws Exception {
        int databaseSizeBeforeUpdate = organizationUnitRepository.findAll().size();

        // Create the OrganizationUnit

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganizationUnitMockMvc.perform(put("/api/organization-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizationUnit)))
            .andExpect(status().isBadRequest());

        // Validate the OrganizationUnit in the database
        List<OrganizationUnit> organizationUnitList = organizationUnitRepository.findAll();
        assertThat(organizationUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganizationUnit() throws Exception {
        // Initialize the database
        organizationUnitService.save(organizationUnit);

        int databaseSizeBeforeDelete = organizationUnitRepository.findAll().size();

        // Delete the organizationUnit
        restOrganizationUnitMockMvc.perform(delete("/api/organization-units/{id}", organizationUnit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrganizationUnit> organizationUnitList = organizationUnitRepository.findAll();
        assertThat(organizationUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
