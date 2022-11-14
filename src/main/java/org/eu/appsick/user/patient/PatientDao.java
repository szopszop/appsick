package org.eu.appsick.user.patient;


import java.util.Optional;

public interface PatientDao {

    Optional<Patient> getPatientById(long patientId);
    void add(Patient patient);
    void remove(Patient patient);
}
