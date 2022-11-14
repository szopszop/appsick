package org.eu.appsick.user.doctor;

import org.eu.appsick.user.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyDoctorService implements DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public MyDoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Optional<Doctor> getDoctorById(long doctorId) {
        return doctorDao.getDoctorById(doctorId);
    }
}
