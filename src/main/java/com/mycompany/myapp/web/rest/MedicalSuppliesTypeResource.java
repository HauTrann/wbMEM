package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MedicalSuppliesType;
import com.mycompany.myapp.service.MedicalSuppliesTypeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.MedicalSuppliesType}.
 */
@RestController
@RequestMapping("/api")
public class MedicalSuppliesTypeResource {

    private final Logger log = LoggerFactory.getLogger(MedicalSuppliesTypeResource.class);

    private static final String ENTITY_NAME = "medicalSuppliesType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalSuppliesTypeService medicalSuppliesTypeService;

    public MedicalSuppliesTypeResource(MedicalSuppliesTypeService medicalSuppliesTypeService) {
        this.medicalSuppliesTypeService = medicalSuppliesTypeService;
    }

    /**
     * {@code POST  /medical-supplies-types} : Create a new medicalSuppliesType.
     *
     * @param medicalSuppliesType the medicalSuppliesType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalSuppliesType, or with status {@code 400 (Bad Request)} if the medicalSuppliesType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-supplies-types")
    public ResponseEntity<MedicalSuppliesType> createMedicalSuppliesType(@RequestBody MedicalSuppliesType medicalSuppliesType) throws URISyntaxException {
        log.debug("REST request to save MedicalSuppliesType : {}", medicalSuppliesType);
        if (medicalSuppliesType.getId() != null) {
            throw new BadRequestAlertException("A new medicalSuppliesType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalSuppliesType result = medicalSuppliesTypeService.save(medicalSuppliesType);
        return ResponseEntity.created(new URI("/api/medical-supplies-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-supplies-types} : Updates an existing medicalSuppliesType.
     *
     * @param medicalSuppliesType the medicalSuppliesType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalSuppliesType,
     * or with status {@code 400 (Bad Request)} if the medicalSuppliesType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalSuppliesType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-supplies-types")
    public ResponseEntity<MedicalSuppliesType> updateMedicalSuppliesType(@RequestBody MedicalSuppliesType medicalSuppliesType) throws URISyntaxException {
        log.debug("REST request to update MedicalSuppliesType : {}", medicalSuppliesType);
        if (medicalSuppliesType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalSuppliesType result = medicalSuppliesTypeService.save(medicalSuppliesType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicalSuppliesType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-supplies-types} : get all the medicalSuppliesTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalSuppliesTypes in body.
     */
    @GetMapping("/medical-supplies-types")
    public ResponseEntity<List<MedicalSuppliesType>> getAllMedicalSuppliesTypes(Pageable pageable) {
        log.debug("REST request to get a page of MedicalSuppliesTypes");
        Page<MedicalSuppliesType> page = medicalSuppliesTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-supplies-types/:id} : get the "id" medicalSuppliesType.
     *
     * @param id the id of the medicalSuppliesType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalSuppliesType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-supplies-types/{id}")
    public ResponseEntity<MedicalSuppliesType> getMedicalSuppliesType(@PathVariable Long id) {
        log.debug("REST request to get MedicalSuppliesType : {}", id);
        Optional<MedicalSuppliesType> medicalSuppliesType = medicalSuppliesTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalSuppliesType);
    }

    /**
     * {@code DELETE  /medical-supplies-types/:id} : delete the "id" medicalSuppliesType.
     *
     * @param id the id of the medicalSuppliesType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-supplies-types/{id}")
    public ResponseEntity<Void> deleteMedicalSuppliesType(@PathVariable Long id) {
        log.debug("REST request to delete MedicalSuppliesType : {}", id);
        medicalSuppliesTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
