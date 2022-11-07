package org.eu.appsick.user.patient;

import org.springframework.stereotype.Service;


@Service
public class DummyPatientService implements PatientService {

    @Override
    public Patient getPatient() {
        return new Patient(
                125,
                "1251525",
                false
        );
    }
}
