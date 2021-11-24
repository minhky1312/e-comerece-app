package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ElectroniceDeviceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElectroniceDevice.class);
        ElectroniceDevice electroniceDevice1 = new ElectroniceDevice();
        electroniceDevice1.setId(1L);
        ElectroniceDevice electroniceDevice2 = new ElectroniceDevice();
        electroniceDevice2.setId(electroniceDevice1.getId());
        assertThat(electroniceDevice1).isEqualTo(electroniceDevice2);
        electroniceDevice2.setId(2L);
        assertThat(electroniceDevice1).isNotEqualTo(electroniceDevice2);
        electroniceDevice1.setId(null);
        assertThat(electroniceDevice1).isNotEqualTo(electroniceDevice2);
    }
}
