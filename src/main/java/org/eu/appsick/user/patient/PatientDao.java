package org.eu.appsick.user.patient;

import java.util.UUID;

public interface PatientDao {

    Patient getById(UUID uuid);
}
