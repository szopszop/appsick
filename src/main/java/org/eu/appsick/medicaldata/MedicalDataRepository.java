package org.eu.appsick.medicaldata;

import org.eu.appsick.user.User;
import org.eu.appsick.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData, Long> {

    Optional<MedicalData> getMedicalDataByMedicalDataId(Long medicalDataId);
    Optional<Set<MedicalData>> getAllByUser(User user);
    Optional<Set<MedicalData>> getAllByVisit(Visit visit);
}
