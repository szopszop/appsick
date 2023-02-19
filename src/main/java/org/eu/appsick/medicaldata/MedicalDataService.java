package org.eu.appsick.medicaldata;

import org.eu.appsick.user.User;
import org.eu.appsick.visit.Visit;

import java.util.Optional;
import java.util.Set;

interface MedicalDataService {
    Optional<MedicalData> getMedicalDataById(Long medicalDataId);
    Optional<Set<MedicalData>> getMedicalDataByUser(User user);
    Optional<Set<MedicalData>> getMedicalDataByVisit(Visit visit);
    void saveMedicalData(MedicalDataDTO medicalData, User user, Visit visit);
}
