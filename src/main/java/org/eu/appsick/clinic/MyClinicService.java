package org.eu.appsick.clinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyClinicService implements ClinicService{

    private final ClinicDao clinicDao;

    @Autowired
    public MyClinicService(ClinicDao clinicDao) {
        this.clinicDao = clinicDao;
    }

    @Override
    public Optional<Clinic> getClinicById(long clinicId) {
        return clinicDao.getClinicById(clinicId);
    }
}
