package com.navjot.clinical.controllers;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.navjot.clinical.model.ClinicalData;
import com.navjot.clinical.model.Patient;
import com.navjot.clinical.repos.PatientRepository;
import com.navjot.clinical.util.BMICalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    "/api"
)
@CrossOrigin
public class PatientController {

    PatientRepository patientRepo;

    Map<String, String> filter = new HashMap<>();

    @Autowired
    PatientController(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    @RequestMapping(
        value = "/patients", method = RequestMethod.GET
    )
    List<Patient> getPatients() {
        return patientRepo.findAll();
    }

    @RequestMapping(
        value = "/patients/{id}", method = RequestMethod.GET
    )
    Patient getPatients(@PathVariable("id") int id) {
        return patientRepo.findById(id).get();
    }

    @RequestMapping(
        value = "/patient", method = RequestMethod.POST
    )
    Patient savePatient(@RequestBody Patient patient) {
        String firstName = patient.getFirstName();
        firstName.isEmpty();
        return patientRepo.save(patient);
    }

    @RequestMapping(
        value = "/patient/analyse",
        method = RequestMethod.GET
    )
    public Patient analyse(@RequestParam Integer patientId) {
        Patient patient = patientRepo.findById(patientId).get();
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> clinicalDataCopy = new ArrayList<>(clinicalData);

        // for (ClinicalData data : clinicalDataCopy) {
        //     if (filter.containsKey(data.getComponentName())) {
        //         clinicalData.remove(data);
        //     } else {
        //         filter.put(data.getComponentName(), data.getComponentValue());
        //         if (data.getComponentName() == "hw") {
        //             clinicalData.add(findPatientClinicalDataBMI(data));
        //         }
        //     }
        // }

        clinicalDataCopy.stream()
        .forEach((ClinicalData data) -> {
            if (filter.containsKey(data.getComponentName())) {
                clinicalData.remove(data);
            } else {
                filter.put(data.getComponentName(), data.getComponentValue());
                if (data.getComponentName().equalsIgnoreCase("hw")) {
                    clinicalData.add(BMICalculator.findPatientClinicalDataBMI(data));
                }
            }
            return;
        });

        filter.clear();
        return patient;
    }
    
}
