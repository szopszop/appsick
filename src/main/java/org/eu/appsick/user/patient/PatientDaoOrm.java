package org.eu.appsick.user.patient;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PatientDaoOrm implements PatientDao {
    @Override
    public Patient getById(UUID uuid) {
        return null;
    }
}
