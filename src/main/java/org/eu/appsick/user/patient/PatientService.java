package org.eu.appsick.user.patient;

import java.util.Optional;

public interface PatientService {

    Optional<Patient> getPatientById(long patientId);
}
