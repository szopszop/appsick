package org.eu.appsick.visit;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Primary
public class DummyVisitService implements VisitService {
    @Override
    public Visit getById(long id) {
        return new Visit(125, 125, 125, 125,
                LocalDateTime.now(), true, "tete", Visit.VisitStatus.PENDING);
    }

    @Override
    public List<Visit> getPatientVisits(long patientId) {
        return null;
    }

    @Override
    public List<Visit> getDoctorVisits(long doctorId) {
        return null;
    }

    @Override
    public boolean addVisit(Visit visit) {
        return false;
    }

    @Override
    public boolean editVisit(long visitID, Visit editedVisit) {
        return false;
    }

    @Override
    public boolean deleteVisit(long visitId) {
        return false;
    }
}