package org.eu.appsick.user.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByDoctorId(Long doctorId);

    List<Doctor> findAllByMedicalSpecialities(Doctor.Speciality speciality);

    @Query(value = "SELECT * " +
            "FROM doctors d " +
            "JOIN doctors_in_clinic dc ON d.doctor_id = dc.doctors_doctor_id " +
            "WHERE dc.clinic_clinic_id = :clinicId", nativeQuery = true)
    List<Doctor> findAllByClinicId(Long clinicId);
}
