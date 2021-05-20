package com.navjot.clinical.repos;

import com.navjot.clinical.model.ClinicalData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

	List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);
    
}
