package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ElectroniceDevice;
import com.mycompany.myapp.repository.ElectroniceDeviceRepository;
import com.mycompany.myapp.service.ElectroniceDeviceService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ElectroniceDevice}.
 */
@RestController
@RequestMapping("/api")
public class ElectroniceDeviceResource {

    private final Logger log = LoggerFactory.getLogger(ElectroniceDeviceResource.class);

    private static final String ENTITY_NAME = "electroniceDevice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElectroniceDeviceService electroniceDeviceService;

    private final ElectroniceDeviceRepository electroniceDeviceRepository;

    public ElectroniceDeviceResource(
        ElectroniceDeviceService electroniceDeviceService,
        ElectroniceDeviceRepository electroniceDeviceRepository
    ) {
        this.electroniceDeviceService = electroniceDeviceService;
        this.electroniceDeviceRepository = electroniceDeviceRepository;
    }

    /**
     * {@code POST  /electronice-devices} : Create a new electroniceDevice.
     *
     * @param electroniceDevice the electroniceDevice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new electroniceDevice, or with status {@code 400 (Bad Request)} if the electroniceDevice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/electronice-devices")
    public ResponseEntity<ElectroniceDevice> createElectroniceDevice(@RequestBody ElectroniceDevice electroniceDevice)
        throws URISyntaxException {
        log.debug("REST request to save ElectroniceDevice : {}", electroniceDevice);
        if (electroniceDevice.getId() != null) {
            throw new BadRequestAlertException("A new electroniceDevice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElectroniceDevice result = electroniceDeviceService.save(electroniceDevice);
        return ResponseEntity
            .created(new URI("/api/electronice-devices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /electronice-devices/:id} : Updates an existing electroniceDevice.
     *
     * @param id the id of the electroniceDevice to save.
     * @param electroniceDevice the electroniceDevice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated electroniceDevice,
     * or with status {@code 400 (Bad Request)} if the electroniceDevice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the electroniceDevice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/electronice-devices/{id}")
    public ResponseEntity<ElectroniceDevice> updateElectroniceDevice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ElectroniceDevice electroniceDevice
    ) throws URISyntaxException {
        log.debug("REST request to update ElectroniceDevice : {}, {}", id, electroniceDevice);
        if (electroniceDevice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, electroniceDevice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!electroniceDeviceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ElectroniceDevice result = electroniceDeviceService.save(electroniceDevice);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, electroniceDevice.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /electronice-devices/:id} : Partial updates given fields of an existing electroniceDevice, field will ignore if it is null
     *
     * @param id the id of the electroniceDevice to save.
     * @param electroniceDevice the electroniceDevice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated electroniceDevice,
     * or with status {@code 400 (Bad Request)} if the electroniceDevice is not valid,
     * or with status {@code 404 (Not Found)} if the electroniceDevice is not found,
     * or with status {@code 500 (Internal Server Error)} if the electroniceDevice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/electronice-devices/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ElectroniceDevice> partialUpdateElectroniceDevice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ElectroniceDevice electroniceDevice
    ) throws URISyntaxException {
        log.debug("REST request to partial update ElectroniceDevice partially : {}, {}", id, electroniceDevice);
        if (electroniceDevice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, electroniceDevice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!electroniceDeviceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ElectroniceDevice> result = electroniceDeviceService.partialUpdate(electroniceDevice);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, electroniceDevice.getId().toString())
        );
    }

    /**
     * {@code GET  /electronice-devices} : get all the electroniceDevices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of electroniceDevices in body.
     */
    @GetMapping("/electronice-devices")
    public ResponseEntity<List<ElectroniceDevice>> getAllElectroniceDevices(Pageable pageable) {
        log.debug("REST request to get a page of ElectroniceDevices");
        Page<ElectroniceDevice> page = electroniceDeviceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /electronice-devices/:id} : get the "id" electroniceDevice.
     *
     * @param id the id of the electroniceDevice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the electroniceDevice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/electronice-devices/{id}")
    public ResponseEntity<ElectroniceDevice> getElectroniceDevice(@PathVariable Long id) {
        log.debug("REST request to get ElectroniceDevice : {}", id);
        Optional<ElectroniceDevice> electroniceDevice = electroniceDeviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(electroniceDevice);
    }

    /**
     * {@code DELETE  /electronice-devices/:id} : delete the "id" electroniceDevice.
     *
     * @param id the id of the electroniceDevice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/electronice-devices/{id}")
    public ResponseEntity<Void> deleteElectroniceDevice(@PathVariable Long id) {
        log.debug("REST request to delete ElectroniceDevice : {}", id);
        electroniceDeviceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
