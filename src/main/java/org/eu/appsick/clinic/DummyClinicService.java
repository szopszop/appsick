package org.eu.appsick.clinic;

import org.springframework.stereotype.Service;



@Service
public class DummyClinicService implements ClinicService {
    @Override
    public Clinic getClinic() {
        return new Clinic(125, "Klinika lux", "1255", "555");
    }
}
