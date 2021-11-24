package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ElectroniceDevice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ElectroniceDevice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElectroniceDeviceRepository extends JpaRepository<ElectroniceDevice, Long> {}
