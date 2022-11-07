package org.eu.appsick.clinic;

import java.util.UUID;

public class DummyClinicService implements ClinicService {
    @Override
    public Clinic getClinic() {
        return new Clinic(UUID.randomUUID(), "Klinika lux", "1255", "555");
    }
}
