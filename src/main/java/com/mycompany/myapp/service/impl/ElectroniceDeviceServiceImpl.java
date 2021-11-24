package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ElectroniceDevice;
import com.mycompany.myapp.repository.ElectroniceDeviceRepository;
import com.mycompany.myapp.service.ElectroniceDeviceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ElectroniceDevice}.
 */
@Service
@Transactional
public class ElectroniceDeviceServiceImpl implements ElectroniceDeviceService {

    private final Logger log = LoggerFactory.getLogger(ElectroniceDeviceServiceImpl.class);

    private final ElectroniceDeviceRepository electroniceDeviceRepository;

    public ElectroniceDeviceServiceImpl(ElectroniceDeviceRepository electroniceDeviceRepository) {
        this.electroniceDeviceRepository = electroniceDeviceRepository;
    }

    @Override
    public ElectroniceDevice save(ElectroniceDevice electroniceDevice) {
        log.debug("Request to save ElectroniceDevice : {}", electroniceDevice);
        return electroniceDeviceRepository.save(electroniceDevice);
    }

    @Override
    public Optional<ElectroniceDevice> partialUpdate(ElectroniceDevice electroniceDevice) {
        log.debug("Request to partially update ElectroniceDevice : {}", electroniceDevice);

        return electroniceDeviceRepository
            .findById(electroniceDevice.getId())
            .map(existingElectroniceDevice -> {
                if (electroniceDevice.getTenSanpham() != null) {
                    existingElectroniceDevice.setTenSanpham(electroniceDevice.getTenSanpham());
                }
                if (electroniceDevice.getGiaBan() != null) {
                    existingElectroniceDevice.setGiaBan(electroniceDevice.getGiaBan());
                }
                if (electroniceDevice.getLoaiSanpham() != null) {
                    existingElectroniceDevice.setLoaiSanpham(electroniceDevice.getLoaiSanpham());
                }
                if (electroniceDevice.getSoluong() != null) {
                    existingElectroniceDevice.setSoluong(electroniceDevice.getSoluong());
                }
                if (electroniceDevice.getSanphammoi() != null) {
                    existingElectroniceDevice.setSanphammoi(electroniceDevice.getSanphammoi());
                }
                if (electroniceDevice.getOverView() != null) {
                    existingElectroniceDevice.setOverView(electroniceDevice.getOverView());
                }
                if (electroniceDevice.getDungLuong() != null) {
                    existingElectroniceDevice.setDungLuong(electroniceDevice.getDungLuong());
                }
                if (electroniceDevice.getMaHoa() != null) {
                    existingElectroniceDevice.setMaHoa(electroniceDevice.getMaHoa());
                }
                if (electroniceDevice.getTocDo() != null) {
                    existingElectroniceDevice.setTocDo(electroniceDevice.getTocDo());
                }
                if (electroniceDevice.getmTBF() != null) {
                    existingElectroniceDevice.setmTBF(electroniceDevice.getmTBF());
                }
                if (electroniceDevice.getnANDFlash() != null) {
                    existingElectroniceDevice.setnANDFlash(electroniceDevice.getnANDFlash());
                }
                if (electroniceDevice.getHeDieuhanhhotro() != null) {
                    existingElectroniceDevice.setHeDieuhanhhotro(electroniceDevice.getHeDieuhanhhotro());
                }

                return existingElectroniceDevice;
            })
            .map(electroniceDeviceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ElectroniceDevice> findAll(Pageable pageable) {
        log.debug("Request to get all ElectroniceDevices");
        return electroniceDeviceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ElectroniceDevice> findOne(Long id) {
        log.debug("Request to get ElectroniceDevice : {}", id);
        return electroniceDeviceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElectroniceDevice : {}", id);
        electroniceDeviceRepository.deleteById(id);
    }
}
