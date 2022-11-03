package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorDao;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VisitService {
    private final VisitDao visitDao;
    private final PatientDao patientDao;
    private final DoctorDao doctorDao;

    public VisitService(VisitDao visitDao, PatientDao patientDao, DoctorDao doctorDao){
        this.visitDao = visitDao;
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
    }

    public Visit getById(String id){
        UUID uuid = UUID.fromString(id);
        Visit visit = visitDao.getVisit(uuid);
        return visit;
    }

    public List<Visit> getPatientVisits(String userId){
        UUID uuid = UUID.fromString(userId);
        Patient patient = patientDao.getById(uuid);
        return visitDao.getVisitList(patient);
    }
    public List<Visit> getDoctorVisits(String userId){
        UUID uuid = UUID.fromString(userId);
        Doctor doctor = doctorDao.getById(uuid);
        return visitDao.getVisitList(doctor);
    }

}
