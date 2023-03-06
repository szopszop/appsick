package org.eu.appsick.user.patient;

import org.eu.appsick.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPatientId(Long patientId);

    @Query(value = "SELECT * FROM patients WHERE patients.user_id = :userId", nativeQuery = true)
    Optional<Patient> findPatientByUserId(Long userId);




}
