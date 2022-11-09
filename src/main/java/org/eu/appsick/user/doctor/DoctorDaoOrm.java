package org.eu.appsick.user.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DoctorDaoOrm implements DoctorDao{

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorDaoOrm(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Optional<Doctor> getDoctorById(long doctorId) {
        return doctorRepository.findByDoctorId(doctorId);
    }

    @Override
    public void add(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void remove(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
