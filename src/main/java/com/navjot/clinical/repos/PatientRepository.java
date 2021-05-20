package com.navjot.clinical.repos;

import com.navjot.clinical.model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PatientRepository
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
