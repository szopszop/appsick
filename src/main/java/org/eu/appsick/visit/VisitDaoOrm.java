package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class VisitDaoOrm implements VisitDao {
    private final List<Visit> visitList;
    public VisitDaoOrm(){
        this.visitList = new ArrayList<>();
    }

    @Override
    public Visit getVisit(UUID visitId) {
        for (Visit visit : visitList){
            if (visit.getVisitId().equals(visitId)){
                return visit;
            }
        }
        return null;
    }

    @Override
    public List<Visit> getVisitList(Patient patient) {
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : visitList){
            if (visit.getPatientId().equals(patient.getUserId())){
                visits.add(visit);
            }
        }
        return visits;
    }

    @Override
    public List<Visit> getVisitList(Doctor doctor) {
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : visitList){
            if (visit.getPatientId().equals(doctor.getUserId())){
                visits.add(visit);
            }
        }
        return visits;
    }

    @Override
    public void addVisit(Visit visit) {
        visitList.add(visit);
    }

    @Override
    public void deleteVisit(Visit visit) {
        visitList.remove(visit);
    }

    @Override
    public void editVisit(UUID visitId, Visit editedVisit){

    }

}
