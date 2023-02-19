package org.eu.appsick.medicaldata;

import org.eu.appsick.user.User;
import org.eu.appsick.user.UserService;
import org.eu.appsick.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MyMedicalDataService implements MedicalDataService {

    private final MedicalDataRepository medicalDataRepository;

    @Autowired
    public MyMedicalDataService(MedicalDataRepository medicalDataRepository) {
        this.medicalDataRepository = medicalDataRepository;
    }

    @Override
    public Optional<MedicalData> getMedicalDataById(Long medicalDataId) {
        return medicalDataRepository.getMedicalDataByMedicalDataId(medicalDataId);
    }

    @Override
    public Optional<Set<MedicalData>> getMedicalDataByUser(User user) {
        return medicalDataRepository.getAllByUser(user);
    }

    @Override
    public Optional<Set<MedicalData>> getMedicalDataByVisit(Visit visit) {
        return medicalDataRepository.getAllByVisit(visit);
    }

    @Override
    public void saveMedicalData(MedicalDataDTO medicalData, User user, Visit visit) {
        medicalDataRepository.save(new MedicalData(
                null,
                medicalData.getWeight(),
                medicalData.getHeight(),
                medicalData.getMedicalConditions(),
                medicalData.getAllergies(),
                medicalData.getAddictions(),
                medicalData.getMedicaments(),
                medicalData.getRecommendations(),
                user,
                visit
        ));
    }
}
