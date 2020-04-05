package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MedicalSuppliesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalSupplies.class);
        MedicalSupplies medicalSupplies1 = new MedicalSupplies();
        medicalSupplies1.setId(1L);
        MedicalSupplies medicalSupplies2 = new MedicalSupplies();
        medicalSupplies2.setId(medicalSupplies1.getId());
        assertThat(medicalSupplies1).isEqualTo(medicalSupplies2);
        medicalSupplies2.setId(2L);
        assertThat(medicalSupplies1).isNotEqualTo(medicalSupplies2);
        medicalSupplies1.setId(null);
        assertThat(medicalSupplies1).isNotEqualTo(medicalSupplies2);
    }
}
