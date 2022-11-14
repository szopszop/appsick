package org.eu.appsick.user.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PatientDaoOrm implements PatientDao {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientDaoOrm(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> getPatientById(long patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    @Override
    public void add(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void remove(Patient patient) {
        patientRepository.delete(patient);
    }
}
