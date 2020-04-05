package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.EquipmentType;
import com.mycompany.myapp.service.EquipmentTypeService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.EquipmentType}.
 */
@RestController
@RequestMapping("/api")
public class EquipmentTypeResource {

    private final Logger log = LoggerFactory.getLogger(EquipmentTypeResource.class);

    private static final String ENTITY_NAME = "equipmentType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EquipmentTypeService equipmentTypeService;

    public EquipmentTypeResource(EquipmentTypeService equipmentTypeService) {
        this.equipmentTypeService = equipmentTypeService;
    }

    /**
     * {@code POST  /equipment-types} : Create a new equipmentType.
     *
     * @param equipmentType the equipmentType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new equipmentType, or with status {@code 400 (Bad Request)} if the equipmentType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/equipment-types")
    public ResponseEntity<EquipmentType> createEquipmentType(@RequestBody EquipmentType equipmentType) throws URISyntaxException {
        log.debug("REST request to save EquipmentType : {}", equipmentType);
        if (equipmentType.getId() != null) {
            throw new BadRequestAlertException("A new equipmentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EquipmentType result = equipmentTypeService.save(equipmentType);
        return ResponseEntity.created(new URI("/api/equipment-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /equipment-types} : Updates an existing equipmentType.
     *
     * @param equipmentType the equipmentType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipmentType,
     * or with status {@code 400 (Bad Request)} if the equipmentType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the equipmentType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/equipment-types")
    public ResponseEntity<EquipmentType> updateEquipmentType(@RequestBody EquipmentType equipmentType) throws URISyntaxException {
        log.debug("REST request to update EquipmentType : {}", equipmentType);
        if (equipmentType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EquipmentType result = equipmentTypeService.save(equipmentType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, equipmentType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /equipment-types} : get all the equipmentTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of equipmentTypes in body.
     */
    @GetMapping("/equipment-types")
    public ResponseEntity<List<EquipmentType>> getAllEquipmentTypes(Pageable pageable) {
        log.debug("REST request to get a page of EquipmentTypes");
        Page<EquipmentType> page = equipmentTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /equipment-types/:id} : get the "id" equipmentType.
     *
     * @param id the id of the equipmentType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the equipmentType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/equipment-types/{id}")
    public ResponseEntity<EquipmentType> getEquipmentType(@PathVariable Long id) {
        log.debug("REST request to get EquipmentType : {}", id);
        Optional<EquipmentType> equipmentType = equipmentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(equipmentType);
    }

    /**
     * {@code DELETE  /equipment-types/:id} : delete the "id" equipmentType.
     *
     * @param id the id of the equipmentType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/equipment-types/{id}")
    public ResponseEntity<Void> deleteEquipmentType(@PathVariable Long id) {
        log.debug("REST request to delete EquipmentType : {}", id);
        equipmentTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
