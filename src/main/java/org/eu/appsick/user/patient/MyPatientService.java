package org.eu.appsick.user.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPatientService implements PatientService{

    private final PatientRepository patientRepository;

    @Autowired
    public MyPatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepository.findByPatientId(patientId);
    }
}
