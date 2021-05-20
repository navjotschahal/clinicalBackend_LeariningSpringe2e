package com.navjot.clinical.controllers;

import com.navjot.clinical.dto.ClinicalDataDto;
import com.navjot.clinical.model.ClinicalData;
import com.navjot.clinical.model.Patient;
import com.navjot.clinical.repos.ClinicalDataRepository;
import com.navjot.clinical.repos.PatientRepository;
import com.navjot.clinical.util.BMICalculator;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    value = "/api"
)
@CrossOrigin
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepository clinicalDataRepo;
    @Autowired
    private PatientRepository patientRepo;

    @RequestMapping(
        value = "/clinicalData",
        method = RequestMethod.POST
    )
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataDto clinicalDataDto) {
        Patient patient = patientRepo.findById(clinicalDataDto.getPatientId()).get();
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(clinicalDataDto.getComponentName());
        clinicalData.setComponentValue(clinicalDataDto.getComponentValue());
        clinicalData.setPatient(patient);

        // ModelMapper modelMapper = new ModelMapper();
        // modelMapper.typeMap(ClinicalDataDto.class, ClinicalData.class).addMappings(mapper -> {
        //     mapper.map(
        //         src -> patientRepo.findById(src.getPatientId()).get(),
        //         (dest, v) -> dest.setPatient((Patient) v)
        //     );
        // });
        // ClinicalData clinicalData = modelMapper.map(clinicalDataDto, ClinicalData.class);

        return clinicalDataRepo.save(clinicalData);
    }
    
    @RequestMapping(
    	value = "/getClinicalData"
    )
    public List<ClinicalData> getClinicalData(@RequestParam int patientId, @RequestParam String componentName) {
    	List<ClinicalData> clinicalData = clinicalDataRepo.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName.equalsIgnoreCase("bmi") ? "hw" : componentName);
        if (componentName.equalsIgnoreCase("bmi")) {
            ArrayList<ClinicalData> bmiClinicalData = new ArrayList<ClinicalData>();
            clinicalData.forEach(data -> {
                bmiClinicalData.add(BMICalculator.findPatientClinicalDataBMI(data));
            });
            return bmiClinicalData;
        }
		return clinicalData;
	}
    
}
