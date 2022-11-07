package org.eu.appsick.user.patient;

import java.util.UUID;

public class DummyPatientService implements PatientService {

    @Override
    public Patient getPatient() {
        return new Patient(
                UUID.randomUUID(),
                UUID.randomUUID(),
                12515251515,
                false
                );
    }
}
