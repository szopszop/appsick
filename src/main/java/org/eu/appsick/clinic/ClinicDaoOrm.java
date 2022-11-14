package org.eu.appsick.clinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClinicDaoOrm implements ClinicDao {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicDaoOrm(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Optional<Clinic> getClinicById(long clinicId) {
        return clinicRepository.findByClinicId(clinicId);
    }

    @Override
    public void add(Clinic clinic) {
        clinicRepository.save(clinic);
    }

    @Override
    public void remove(Clinic clinic) {
        clinicRepository.delete(clinic);
    }
}
