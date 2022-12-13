package org.eu.appsick.user.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByDoctorId(Long doctorId);

    List<Doctor> findAllByMedicalSpecialities(Doctor.Speciality speciality);
}
