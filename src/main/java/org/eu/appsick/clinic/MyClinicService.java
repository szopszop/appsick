package org.eu.appsick.clinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyClinicService implements ClinicService{

    private final ClinicRepository clinicRepository;

    @Autowired
    public MyClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Optional<Clinic> getClinicById(Long clinicId) {
        return clinicRepository.findByClinicId(clinicId);
    }

    @Override
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

}
