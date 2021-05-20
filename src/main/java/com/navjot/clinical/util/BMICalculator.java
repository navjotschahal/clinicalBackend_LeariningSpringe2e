package com.navjot.clinical.util;

import com.navjot.clinical.model.ClinicalData;

public class BMICalculator {

	public static ClinicalData findPatientClinicalDataBMI(ClinicalData data) {
        String[] heightAndWeight = data.getComponentValue().split("/", 2);
        ClinicalData bmi = new ClinicalData();
        bmi.setComponentValue(Float.toString(Float.parseFloat(heightAndWeight[1])/((float) Math.pow(Float.parseFloat(heightAndWeight[0]) * 0.3048, 2))));
        bmi.setComponentName("bmi");
        return bmi;
    }
	
}
