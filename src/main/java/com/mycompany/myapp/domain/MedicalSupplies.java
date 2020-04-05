package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MedicalSupplies.
 */
@Entity
@Table(name = "medical_supplies")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MedicalSupplies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "medical_supplies_type_id")
    private Long medicalSuppliesTypeID;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public MedicalSupplies code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public MedicalSupplies name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMedicalSuppliesTypeID() {
        return medicalSuppliesTypeID;
    }

    public MedicalSupplies medicalSuppliesTypeID(Long medicalSuppliesTypeID) {
        this.medicalSuppliesTypeID = medicalSuppliesTypeID;
        return this;
    }

    public void setMedicalSuppliesTypeID(Long medicalSuppliesTypeID) {
        this.medicalSuppliesTypeID = medicalSuppliesTypeID;
    }

    public String getDescription() {
        return description;
    }

    public MedicalSupplies description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public MedicalSupplies status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalSupplies)) {
            return false;
        }
        return id != null && id.equals(((MedicalSupplies) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MedicalSupplies{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", medicalSuppliesTypeID=" + getMedicalSuppliesTypeID() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
