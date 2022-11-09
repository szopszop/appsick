package org.eu.appsick.user.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPatientService implements PatientService{

    private final PatientDao patientDao;

    @Autowired
    public MyPatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public Optional<Patient> getPatientById(long patientId) {
        return patientDao.getPatientById(patientId);
    }
}
