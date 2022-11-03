package org.eu.appsick.user.doctor;

import java.util.UUID;

public interface DoctorDao {

    Doctor getById(UUID uuid);

}
