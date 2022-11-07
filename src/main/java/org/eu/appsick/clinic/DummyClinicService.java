package org.eu.appsick.clinic;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DummyClinicService implements ClinicService {
    @Override
    public Clinic getClinic() {
        return new Clinic(UUID.randomUUID(), "Klinika lux", "1255", "555");
    }
}
