package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MedicalSupplies;
import com.mycompany.myapp.service.MedicalSuppliesService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MedicalSupplies}.
 */
@RestController
@RequestMapping("/api")
public class MedicalSuppliesResource {

    private final Logger log = LoggerFactory.getLogger(MedicalSuppliesResource.class);

    private static final String ENTITY_NAME = "medicalSupplies";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalSuppliesService medicalSuppliesService;

    public MedicalSuppliesResource(MedicalSuppliesService medicalSuppliesService) {
        this.medicalSuppliesService = medicalSuppliesService;
    }

    /**
     * {@code POST  /medical-supplies} : Create a new medicalSupplies.
     *
     * @param medicalSupplies the medicalSupplies to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalSupplies, or with status {@code 400 (Bad Request)} if the medicalSupplies has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-supplies")
    public ResponseEntity<MedicalSupplies> createMedicalSupplies(@RequestBody MedicalSupplies medicalSupplies) throws URISyntaxException {
        log.debug("REST request to save MedicalSupplies : {}", medicalSupplies);
        if (medicalSupplies.getId() != null) {
            throw new BadRequestAlertException("A new medicalSupplies cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalSupplies result = medicalSuppliesService.save(medicalSupplies);
        return ResponseEntity.created(new URI("/api/medical-supplies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-supplies} : Updates an existing medicalSupplies.
     *
     * @param medicalSupplies the medicalSupplies to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalSupplies,
     * or with status {@code 400 (Bad Request)} if the medicalSupplies is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalSupplies couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-supplies")
    public ResponseEntity<MedicalSupplies> updateMedicalSupplies(@RequestBody MedicalSupplies medicalSupplies) throws URISyntaxException {
        log.debug("REST request to update MedicalSupplies : {}", medicalSupplies);
        if (medicalSupplies.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalSupplies result = medicalSuppliesService.save(medicalSupplies);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicalSupplies.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-supplies} : get all the medicalSupplies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalSupplies in body.
     */
    @GetMapping("/medical-supplies")
    public ResponseEntity<List<MedicalSupplies>> getAllMedicalSupplies(Pageable pageable) {
        log.debug("REST request to get a page of MedicalSupplies");
        Page<MedicalSupplies> page = medicalSuppliesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-supplies/:id} : get the "id" medicalSupplies.
     *
     * @param id the id of the medicalSupplies to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalSupplies, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-supplies/{id}")
    public ResponseEntity<MedicalSupplies> getMedicalSupplies(@PathVariable Long id) {
        log.debug("REST request to get MedicalSupplies : {}", id);
        Optional<MedicalSupplies> medicalSupplies = medicalSuppliesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalSupplies);
    }

    /**
     * {@code DELETE  /medical-supplies/:id} : delete the "id" medicalSupplies.
     *
     * @param id the id of the medicalSupplies to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-supplies/{id}")
    public ResponseEntity<Void> deleteMedicalSupplies(@PathVariable Long id) {
        log.debug("REST request to delete MedicalSupplies : {}", id);
        medicalSuppliesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
