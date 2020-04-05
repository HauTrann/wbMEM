package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MedicalSuppliesTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalSuppliesType.class);
        MedicalSuppliesType medicalSuppliesType1 = new MedicalSuppliesType();
        medicalSuppliesType1.setId(1L);
        MedicalSuppliesType medicalSuppliesType2 = new MedicalSuppliesType();
        medicalSuppliesType2.setId(medicalSuppliesType1.getId());
        assertThat(medicalSuppliesType1).isEqualTo(medicalSuppliesType2);
        medicalSuppliesType2.setId(2L);
        assertThat(medicalSuppliesType1).isNotEqualTo(medicalSuppliesType2);
        medicalSuppliesType1.setId(null);
        assertThat(medicalSuppliesType1).isNotEqualTo(medicalSuppliesType2);
    }
}
