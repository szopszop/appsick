package org.eu.appsick.user.doctor;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DoctorDaoOrm implements DoctorDao{
    @Override
    public Doctor getById(UUID uuid) {
        return null;
    }
}
