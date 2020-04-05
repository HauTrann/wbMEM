package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DepartmentEmployee.
 */
@Entity
@Table(name = "department_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DepartmentEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "employee_id")
    private Long employeeID;

    @Column(name = "department_id")
    private Long departmentID;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public DepartmentEmployee employeeID(Long employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public DepartmentEmployee departmentID(Long departmentID) {
        this.departmentID = departmentID;
        return this;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public Integer getStatus() {
        return status;
    }

    public DepartmentEmployee status(Integer status) {
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
        if (!(o instanceof DepartmentEmployee)) {
            return false;
        }
        return id != null && id.equals(((DepartmentEmployee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
            "id=" + getId() +
            ", employeeID=" + getEmployeeID() +
            ", departmentID=" + getDepartmentID() +
            ", status=" + getStatus() +
            "}";
    }
}
