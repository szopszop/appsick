package org.eu.appsick.clinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Optional<Clinic> findByClinicId(Long clinicId);
}
