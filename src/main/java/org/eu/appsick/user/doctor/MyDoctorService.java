package org.eu.appsick.user.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyDoctorService implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public MyDoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Optional<Doctor> getDoctorById(Long doctorId) {
        return doctorRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Doctor> getDoctorsByClinic(Long clinicId) {
        return doctorRepository.findAll(); // TODO: change
    }

    @Override
    public List<Doctor> getDoctorsBySpeciality(Doctor.Speciality speciality) {
        return doctorRepository.findAllByMedicalSpecialities(speciality);
    }


}
