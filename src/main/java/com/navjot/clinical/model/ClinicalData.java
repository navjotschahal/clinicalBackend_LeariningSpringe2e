package com.navjot.clinical.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
    name = "clinicaldata"
)
public class ClinicalData {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(name="measured_date_time", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp measuredDateTime;
    private String componentValue;
    private String componentName;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "patient_id", nullable = false
    )
    @JsonIgnore()
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getMeasuredDateTime() {
        return measuredDateTime;
    }
    public void setMeasuredDateTime(Timestamp measuredDateTime) {
        this.measuredDateTime = measuredDateTime;
    }
    public String getComponentValue() {
        return componentValue;
    }
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }
    public String getComponentName() {
        return componentName;
    }
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    @Override
    public String toString() {
        return "ClinicalData [componentName=" + componentName + ", componentValue=" + componentValue + ", id=" + id
                + ", measuredDateTime=" + measuredDateTime + ", patient=" + patient + "]";
    }
    
}
