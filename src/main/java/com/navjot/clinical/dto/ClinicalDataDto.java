package com.navjot.clinical.dto;

public class ClinicalDataDto {

    private String componentValue;
    private String componentName;
    private int patientId;

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
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "ClinicalDataDto [componentName=" + componentName + ", componentValue=" + componentValue + ", patientId="
                + patientId + "]";
    }

}
