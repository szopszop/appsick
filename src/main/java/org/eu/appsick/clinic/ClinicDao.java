package org.eu.appsick.clinic;

import java.util.Optional;

public interface ClinicDao {

    Optional<Clinic> getClinicById(long clinicId);
    void add(Clinic clinic);
    void remove(Clinic clinic);
}
