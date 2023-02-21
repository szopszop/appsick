package org.eu.appsick.clinic;

import java.util.List;
import java.util.Optional;

public interface ClinicService {

    Optional<Clinic> getClinicById(Long clinicId);
    List<Clinic> getAllClinics();
}
