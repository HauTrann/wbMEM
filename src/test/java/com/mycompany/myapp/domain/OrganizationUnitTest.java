package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class OrganizationUnitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganizationUnit.class);
        OrganizationUnit organizationUnit1 = new OrganizationUnit();
        organizationUnit1.setId(1L);
        OrganizationUnit organizationUnit2 = new OrganizationUnit();
        organizationUnit2.setId(organizationUnit1.getId());
        assertThat(organizationUnit1).isEqualTo(organizationUnit2);
        organizationUnit2.setId(2L);
        assertThat(organizationUnit1).isNotEqualTo(organizationUnit2);
        organizationUnit1.setId(null);
        assertThat(organizationUnit1).isNotEqualTo(organizationUnit2);
    }
}
