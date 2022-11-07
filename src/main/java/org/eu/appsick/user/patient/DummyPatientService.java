package org.eu.appsick.user.patient;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DummyPatientService implements PatientService {

    @Override
    public Patient getPatient() {
        return new Patient(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "1251525",
                false
                );
    }
}
