package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ElectroniceDevice;
import com.mycompany.myapp.domain.enumeration.Loaisanpham;
import com.mycompany.myapp.repository.ElectroniceDeviceRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ElectroniceDeviceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ElectroniceDeviceResourceIT {

    private static final String DEFAULT_TEN_SANPHAM = "AAAAAAAAAA";
    private static final String UPDATED_TEN_SANPHAM = "BBBBBBBBBB";

    private static final Long DEFAULT_GIA_BAN = 1L;
    private static final Long UPDATED_GIA_BAN = 2L;

    private static final Loaisanpham DEFAULT_LOAI_SANPHAM = Loaisanpham.DIENTHOAI;
    private static final Loaisanpham UPDATED_LOAI_SANPHAM = Loaisanpham.MAYTINHXACHTAY;

    private static final Long DEFAULT_SOLUONG = 1L;
    private static final Long UPDATED_SOLUONG = 2L;

    private static final Boolean DEFAULT_SANPHAMMOI = false;
    private static final Boolean UPDATED_SANPHAMMOI = true;

    private static final String DEFAULT_OVER_VIEW = "AAAAAAAAAA";
    private static final String UPDATED_OVER_VIEW = "BBBBBBBBBB";

    private static final String DEFAULT_DUNG_LUONG = "AAAAAAAAAA";
    private static final String UPDATED_DUNG_LUONG = "BBBBBBBBBB";

    private static final String DEFAULT_MA_HOA = "AAAAAAAAAA";
    private static final String UPDATED_MA_HOA = "BBBBBBBBBB";

    private static final String DEFAULT_TOC_DO = "AAAAAAAAAA";
    private static final String UPDATED_TOC_DO = "BBBBBBBBBB";

    private static final String DEFAULT_M_TBF = "AAAAAAAAAA";
    private static final String UPDATED_M_TBF = "BBBBBBBBBB";

    private static final String DEFAULT_N_AND_FLASH = "AAAAAAAAAA";
    private static final String UPDATED_N_AND_FLASH = "BBBBBBBBBB";

    private static final String DEFAULT_HE_DIEUHANHHOTRO = "AAAAAAAAAA";
    private static final String UPDATED_HE_DIEUHANHHOTRO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/electronice-devices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ElectroniceDeviceRepository electroniceDeviceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElectroniceDeviceMockMvc;

    private ElectroniceDevice electroniceDevice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElectroniceDevice createEntity(EntityManager em) {
        ElectroniceDevice electroniceDevice = new ElectroniceDevice()
            .tenSanpham(DEFAULT_TEN_SANPHAM)
            .giaBan(DEFAULT_GIA_BAN)
            .loaiSanpham(DEFAULT_LOAI_SANPHAM)
            .soluong(DEFAULT_SOLUONG)
            .sanphammoi(DEFAULT_SANPHAMMOI)
            .overView(DEFAULT_OVER_VIEW)
            .dungLuong(DEFAULT_DUNG_LUONG)
            .maHoa(DEFAULT_MA_HOA)
            .tocDo(DEFAULT_TOC_DO)
            .mTBF(DEFAULT_M_TBF)
            .nANDFlash(DEFAULT_N_AND_FLASH)
            .heDieuhanhhotro(DEFAULT_HE_DIEUHANHHOTRO);
        return electroniceDevice;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElectroniceDevice createUpdatedEntity(EntityManager em) {
        ElectroniceDevice electroniceDevice = new ElectroniceDevice()
            .tenSanpham(UPDATED_TEN_SANPHAM)
            .giaBan(UPDATED_GIA_BAN)
            .loaiSanpham(UPDATED_LOAI_SANPHAM)
            .soluong(UPDATED_SOLUONG)
            .sanphammoi(UPDATED_SANPHAMMOI)
            .overView(UPDATED_OVER_VIEW)
            .dungLuong(UPDATED_DUNG_LUONG)
            .maHoa(UPDATED_MA_HOA)
            .tocDo(UPDATED_TOC_DO)
            .mTBF(UPDATED_M_TBF)
            .nANDFlash(UPDATED_N_AND_FLASH)
            .heDieuhanhhotro(UPDATED_HE_DIEUHANHHOTRO);
        return electroniceDevice;
    }

    @BeforeEach
    public void initTest() {
        electroniceDevice = createEntity(em);
    }

    @Test
    @Transactional
    void createElectroniceDevice() throws Exception {
        int databaseSizeBeforeCreate = electroniceDeviceRepository.findAll().size();
        // Create the ElectroniceDevice
        restElectroniceDeviceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isCreated());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeCreate + 1);
        ElectroniceDevice testElectroniceDevice = electroniceDeviceList.get(electroniceDeviceList.size() - 1);
        assertThat(testElectroniceDevice.getTenSanpham()).isEqualTo(DEFAULT_TEN_SANPHAM);
        assertThat(testElectroniceDevice.getGiaBan()).isEqualTo(DEFAULT_GIA_BAN);
        assertThat(testElectroniceDevice.getLoaiSanpham()).isEqualTo(DEFAULT_LOAI_SANPHAM);
        assertThat(testElectroniceDevice.getSoluong()).isEqualTo(DEFAULT_SOLUONG);
        assertThat(testElectroniceDevice.getSanphammoi()).isEqualTo(DEFAULT_SANPHAMMOI);
        assertThat(testElectroniceDevice.getOverView()).isEqualTo(DEFAULT_OVER_VIEW);
        assertThat(testElectroniceDevice.getDungLuong()).isEqualTo(DEFAULT_DUNG_LUONG);
        assertThat(testElectroniceDevice.getMaHoa()).isEqualTo(DEFAULT_MA_HOA);
        assertThat(testElectroniceDevice.getTocDo()).isEqualTo(DEFAULT_TOC_DO);
        assertThat(testElectroniceDevice.getmTBF()).isEqualTo(DEFAULT_M_TBF);
        assertThat(testElectroniceDevice.getnANDFlash()).isEqualTo(DEFAULT_N_AND_FLASH);
        assertThat(testElectroniceDevice.getHeDieuhanhhotro()).isEqualTo(DEFAULT_HE_DIEUHANHHOTRO);
    }

    @Test
    @Transactional
    void createElectroniceDeviceWithExistingId() throws Exception {
        // Create the ElectroniceDevice with an existing ID
        electroniceDevice.setId(1L);

        int databaseSizeBeforeCreate = electroniceDeviceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restElectroniceDeviceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllElectroniceDevices() throws Exception {
        // Initialize the database
        electroniceDeviceRepository.saveAndFlush(electroniceDevice);

        // Get all the electroniceDeviceList
        restElectroniceDeviceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(electroniceDevice.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenSanpham").value(hasItem(DEFAULT_TEN_SANPHAM)))
            .andExpect(jsonPath("$.[*].giaBan").value(hasItem(DEFAULT_GIA_BAN.intValue())))
            .andExpect(jsonPath("$.[*].loaiSanpham").value(hasItem(DEFAULT_LOAI_SANPHAM.toString())))
            .andExpect(jsonPath("$.[*].soluong").value(hasItem(DEFAULT_SOLUONG.intValue())))
            .andExpect(jsonPath("$.[*].sanphammoi").value(hasItem(DEFAULT_SANPHAMMOI.booleanValue())))
            .andExpect(jsonPath("$.[*].overView").value(hasItem(DEFAULT_OVER_VIEW)))
            .andExpect(jsonPath("$.[*].dungLuong").value(hasItem(DEFAULT_DUNG_LUONG)))
            .andExpect(jsonPath("$.[*].maHoa").value(hasItem(DEFAULT_MA_HOA)))
            .andExpect(jsonPath("$.[*].tocDo").value(hasItem(DEFAULT_TOC_DO)))
            .andExpect(jsonPath("$.[*].mTBF").value(hasItem(DEFAULT_M_TBF)))
            .andExpect(jsonPath("$.[*].nANDFlash").value(hasItem(DEFAULT_N_AND_FLASH)))
            .andExpect(jsonPath("$.[*].heDieuhanhhotro").value(hasItem(DEFAULT_HE_DIEUHANHHOTRO)));
    }

    @Test
    @Transactional
    void getElectroniceDevice() throws Exception {
        // Initialize the database
        electroniceDeviceRepository.saveAndFlush(electroniceDevice);

        // Get the electroniceDevice
        restElectroniceDeviceMockMvc
            .perform(get(ENTITY_API_URL_ID, electroniceDevice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(electroniceDevice.getId().intValue()))
            .andExpect(jsonPath("$.tenSanpham").value(DEFAULT_TEN_SANPHAM))
            .andExpect(jsonPath("$.giaBan").value(DEFAULT_GIA_BAN.intValue()))
            .andExpect(jsonPath("$.loaiSanpham").value(DEFAULT_LOAI_SANPHAM.toString()))
            .andExpect(jsonPath("$.soluong").value(DEFAULT_SOLUONG.intValue()))
            .andExpect(jsonPath("$.sanphammoi").value(DEFAULT_SANPHAMMOI.booleanValue()))
            .andExpect(jsonPath("$.overView").value(DEFAULT_OVER_VIEW))
            .andExpect(jsonPath("$.dungLuong").value(DEFAULT_DUNG_LUONG))
            .andExpect(jsonPath("$.maHoa").value(DEFAULT_MA_HOA))
            .andExpect(jsonPath("$.tocDo").value(DEFAULT_TOC_DO))
            .andExpect(jsonPath("$.mTBF").value(DEFAULT_M_TBF))
            .andExpect(jsonPath("$.nANDFlash").value(DEFAULT_N_AND_FLASH))
            .andExpect(jsonPath("$.heDieuhanhhotro").value(DEFAULT_HE_DIEUHANHHOTRO));
    }

    @Test
    @Transactional
    void getNonExistingElectroniceDevice() throws Exception {
        // Get the electroniceDevice
        restElectroniceDeviceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewElectroniceDevice() throws Exception {
        // Initialize the database
        electroniceDeviceRepository.saveAndFlush(electroniceDevice);

        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();

        // Update the electroniceDevice
        ElectroniceDevice updatedElectroniceDevice = electroniceDeviceRepository.findById(electroniceDevice.getId()).get();
        // Disconnect from session so that the updates on updatedElectroniceDevice are not directly saved in db
        em.detach(updatedElectroniceDevice);
        updatedElectroniceDevice
            .tenSanpham(UPDATED_TEN_SANPHAM)
            .giaBan(UPDATED_GIA_BAN)
            .loaiSanpham(UPDATED_LOAI_SANPHAM)
            .soluong(UPDATED_SOLUONG)
            .sanphammoi(UPDATED_SANPHAMMOI)
            .overView(UPDATED_OVER_VIEW)
            .dungLuong(UPDATED_DUNG_LUONG)
            .maHoa(UPDATED_MA_HOA)
            .tocDo(UPDATED_TOC_DO)
            .mTBF(UPDATED_M_TBF)
            .nANDFlash(UPDATED_N_AND_FLASH)
            .heDieuhanhhotro(UPDATED_HE_DIEUHANHHOTRO);

        restElectroniceDeviceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedElectroniceDevice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedElectroniceDevice))
            )
            .andExpect(status().isOk());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
        ElectroniceDevice testElectroniceDevice = electroniceDeviceList.get(electroniceDeviceList.size() - 1);
        assertThat(testElectroniceDevice.getTenSanpham()).isEqualTo(UPDATED_TEN_SANPHAM);
        assertThat(testElectroniceDevice.getGiaBan()).isEqualTo(UPDATED_GIA_BAN);
        assertThat(testElectroniceDevice.getLoaiSanpham()).isEqualTo(UPDATED_LOAI_SANPHAM);
        assertThat(testElectroniceDevice.getSoluong()).isEqualTo(UPDATED_SOLUONG);
        assertThat(testElectroniceDevice.getSanphammoi()).isEqualTo(UPDATED_SANPHAMMOI);
        assertThat(testElectroniceDevice.getOverView()).isEqualTo(UPDATED_OVER_VIEW);
        assertThat(testElectroniceDevice.getDungLuong()).isEqualTo(UPDATED_DUNG_LUONG);
        assertThat(testElectroniceDevice.getMaHoa()).isEqualTo(UPDATED_MA_HOA);
        assertThat(testElectroniceDevice.getTocDo()).isEqualTo(UPDATED_TOC_DO);
        assertThat(testElectroniceDevice.getmTBF()).isEqualTo(UPDATED_M_TBF);
        assertThat(testElectroniceDevice.getnANDFlash()).isEqualTo(UPDATED_N_AND_FLASH);
        assertThat(testElectroniceDevice.getHeDieuhanhhotro()).isEqualTo(UPDATED_HE_DIEUHANHHOTRO);
    }

    @Test
    @Transactional
    void putNonExistingElectroniceDevice() throws Exception {
        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();
        electroniceDevice.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElectroniceDeviceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, electroniceDevice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchElectroniceDevice() throws Exception {
        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();
        electroniceDevice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElectroniceDeviceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamElectroniceDevice() throws Exception {
        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();
        electroniceDevice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElectroniceDeviceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateElectroniceDeviceWithPatch() throws Exception {
        // Initialize the database
        electroniceDeviceRepository.saveAndFlush(electroniceDevice);

        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();

        // Update the electroniceDevice using partial update
        ElectroniceDevice partialUpdatedElectroniceDevice = new ElectroniceDevice();
        partialUpdatedElectroniceDevice.setId(electroniceDevice.getId());

        partialUpdatedElectroniceDevice
            .loaiSanpham(UPDATED_LOAI_SANPHAM)
            .soluong(UPDATED_SOLUONG)
            .sanphammoi(UPDATED_SANPHAMMOI)
            .maHoa(UPDATED_MA_HOA)
            .mTBF(UPDATED_M_TBF)
            .nANDFlash(UPDATED_N_AND_FLASH);

        restElectroniceDeviceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedElectroniceDevice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedElectroniceDevice))
            )
            .andExpect(status().isOk());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
        ElectroniceDevice testElectroniceDevice = electroniceDeviceList.get(electroniceDeviceList.size() - 1);
        assertThat(testElectroniceDevice.getTenSanpham()).isEqualTo(DEFAULT_TEN_SANPHAM);
        assertThat(testElectroniceDevice.getGiaBan()).isEqualTo(DEFAULT_GIA_BAN);
        assertThat(testElectroniceDevice.getLoaiSanpham()).isEqualTo(UPDATED_LOAI_SANPHAM);
        assertThat(testElectroniceDevice.getSoluong()).isEqualTo(UPDATED_SOLUONG);
        assertThat(testElectroniceDevice.getSanphammoi()).isEqualTo(UPDATED_SANPHAMMOI);
        assertThat(testElectroniceDevice.getOverView()).isEqualTo(DEFAULT_OVER_VIEW);
        assertThat(testElectroniceDevice.getDungLuong()).isEqualTo(DEFAULT_DUNG_LUONG);
        assertThat(testElectroniceDevice.getMaHoa()).isEqualTo(UPDATED_MA_HOA);
        assertThat(testElectroniceDevice.getTocDo()).isEqualTo(DEFAULT_TOC_DO);
        assertThat(testElectroniceDevice.getmTBF()).isEqualTo(UPDATED_M_TBF);
        assertThat(testElectroniceDevice.getnANDFlash()).isEqualTo(UPDATED_N_AND_FLASH);
        assertThat(testElectroniceDevice.getHeDieuhanhhotro()).isEqualTo(DEFAULT_HE_DIEUHANHHOTRO);
    }

    @Test
    @Transactional
    void fullUpdateElectroniceDeviceWithPatch() throws Exception {
        // Initialize the database
        electroniceDeviceRepository.saveAndFlush(electroniceDevice);

        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();

        // Update the electroniceDevice using partial update
        ElectroniceDevice partialUpdatedElectroniceDevice = new ElectroniceDevice();
        partialUpdatedElectroniceDevice.setId(electroniceDevice.getId());

        partialUpdatedElectroniceDevice
            .tenSanpham(UPDATED_TEN_SANPHAM)
            .giaBan(UPDATED_GIA_BAN)
            .loaiSanpham(UPDATED_LOAI_SANPHAM)
            .soluong(UPDATED_SOLUONG)
            .sanphammoi(UPDATED_SANPHAMMOI)
            .overView(UPDATED_OVER_VIEW)
            .dungLuong(UPDATED_DUNG_LUONG)
            .maHoa(UPDATED_MA_HOA)
            .tocDo(UPDATED_TOC_DO)
            .mTBF(UPDATED_M_TBF)
            .nANDFlash(UPDATED_N_AND_FLASH)
            .heDieuhanhhotro(UPDATED_HE_DIEUHANHHOTRO);

        restElectroniceDeviceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedElectroniceDevice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedElectroniceDevice))
            )
            .andExpect(status().isOk());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
        ElectroniceDevice testElectroniceDevice = electroniceDeviceList.get(electroniceDeviceList.size() - 1);
        assertThat(testElectroniceDevice.getTenSanpham()).isEqualTo(UPDATED_TEN_SANPHAM);
        assertThat(testElectroniceDevice.getGiaBan()).isEqualTo(UPDATED_GIA_BAN);
        assertThat(testElectroniceDevice.getLoaiSanpham()).isEqualTo(UPDATED_LOAI_SANPHAM);
        assertThat(testElectroniceDevice.getSoluong()).isEqualTo(UPDATED_SOLUONG);
        assertThat(testElectroniceDevice.getSanphammoi()).isEqualTo(UPDATED_SANPHAMMOI);
        assertThat(testElectroniceDevice.getOverView()).isEqualTo(UPDATED_OVER_VIEW);
        assertThat(testElectroniceDevice.getDungLuong()).isEqualTo(UPDATED_DUNG_LUONG);
        assertThat(testElectroniceDevice.getMaHoa()).isEqualTo(UPDATED_MA_HOA);
        assertThat(testElectroniceDevice.getTocDo()).isEqualTo(UPDATED_TOC_DO);
        assertThat(testElectroniceDevice.getmTBF()).isEqualTo(UPDATED_M_TBF);
        assertThat(testElectroniceDevice.getnANDFlash()).isEqualTo(UPDATED_N_AND_FLASH);
        assertThat(testElectroniceDevice.getHeDieuhanhhotro()).isEqualTo(UPDATED_HE_DIEUHANHHOTRO);
    }

    @Test
    @Transactional
    void patchNonExistingElectroniceDevice() throws Exception {
        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();
        electroniceDevice.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElectroniceDeviceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, electroniceDevice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchElectroniceDevice() throws Exception {
        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();
        electroniceDevice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElectroniceDeviceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isBadRequest());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamElectroniceDevice() throws Exception {
        int databaseSizeBeforeUpdate = electroniceDeviceRepository.findAll().size();
        electroniceDevice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restElectroniceDeviceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(electroniceDevice))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ElectroniceDevice in the database
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteElectroniceDevice() throws Exception {
        // Initialize the database
        electroniceDeviceRepository.saveAndFlush(electroniceDevice);

        int databaseSizeBeforeDelete = electroniceDeviceRepository.findAll().size();

        // Delete the electroniceDevice
        restElectroniceDeviceMockMvc
            .perform(delete(ENTITY_API_URL_ID, electroniceDevice.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElectroniceDevice> electroniceDeviceList = electroniceDeviceRepository.findAll();
        assertThat(electroniceDeviceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
