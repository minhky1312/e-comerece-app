package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ElectroniceDevice;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link ElectroniceDevice}.
 */
public interface ElectroniceDeviceService {
    /**
     * Save a electroniceDevice.
     *
     * @param electroniceDevice the entity to save.
     * @return the persisted entity.
     */
    ElectroniceDevice save(ElectroniceDevice electroniceDevice);

    /**
     * Partially updates a electroniceDevice.
     *
     * @param electroniceDevice the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ElectroniceDevice> partialUpdate(ElectroniceDevice electroniceDevice);

    /**
     * Get all the electroniceDevices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElectroniceDevice> findAll(Pageable pageable);

    /**
     * Get the "id" electroniceDevice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElectroniceDevice> findOne(Long id);

    /**
     * Delete the "id" electroniceDevice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
