package org.eu.appsick.clinic;

import java.util.Optional;

public interface ClinicService {

    Optional<Clinic> getClinicById(long clinicId);
}
