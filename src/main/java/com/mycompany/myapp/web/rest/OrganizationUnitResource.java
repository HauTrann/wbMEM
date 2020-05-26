package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OrganizationUnit;
import com.mycompany.myapp.service.OrganizationUnitService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OrganizationUnit}.
 */
@RestController
@RequestMapping("/api")
public class OrganizationUnitResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationUnitResource.class);

    private static final String ENTITY_NAME = "organizationUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganizationUnitService organizationUnitService;

    public OrganizationUnitResource(OrganizationUnitService organizationUnitService) {
        this.organizationUnitService = organizationUnitService;
    }

    /**
     * {@code POST  /organization-units} : Create a new organizationUnit.
     *
     * @param organizationUnit the organizationUnit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organizationUnit, or with status {@code 400 (Bad Request)} if the organizationUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organization-units")
    public ResponseEntity<OrganizationUnit> createOrganizationUnit(@RequestBody OrganizationUnit organizationUnit) throws URISyntaxException {
        log.debug("REST request to save OrganizationUnit : {}", organizationUnit);
        if (organizationUnit.getId() != null) {
            throw new BadRequestAlertException("A new organizationUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganizationUnit result = organizationUnitService.save(organizationUnit);
        return ResponseEntity.created(new URI("/api/organization-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organization-units} : Updates an existing organizationUnit.
     *
     * @param organizationUnit the organizationUnit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organizationUnit,
     * or with status {@code 400 (Bad Request)} if the organizationUnit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organizationUnit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organization-units")
    public ResponseEntity<OrganizationUnit> updateOrganizationUnit(@RequestBody OrganizationUnit organizationUnit) throws URISyntaxException {
        log.debug("REST request to update OrganizationUnit : {}", organizationUnit);
        if (organizationUnit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganizationUnit result = organizationUnitService.save(organizationUnit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, organizationUnit.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organization-units} : get all the organizationUnits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organizationUnits in body.
     */
    @GetMapping("/organization-units")
    public ResponseEntity<List<OrganizationUnit>> getAllOrganizationUnits(Pageable pageable) {
        log.debug("REST request to get a page of OrganizationUnits");
        Page<OrganizationUnit> page = organizationUnitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /organization-units/:id} : get the "id" organizationUnit.
     *
     * @param id the id of the organizationUnit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organizationUnit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organization-units/{id}")
    public ResponseEntity<OrganizationUnit> getOrganizationUnit(@PathVariable Long id) {
        log.debug("REST request to get OrganizationUnit : {}", id);
        Optional<OrganizationUnit> organizationUnit = organizationUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizationUnit);
    }

    /**
     * {@code DELETE  /organization-units/:id} : delete the "id" organizationUnit.
     *
     * @param id the id of the organizationUnit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organization-units/{id}")
    public ResponseEntity<Void> deleteOrganizationUnit(@PathVariable Long id) {
        log.debug("REST request to delete OrganizationUnit : {}", id);
        organizationUnitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
