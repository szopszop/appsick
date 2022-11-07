package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicRepository;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorRepository;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyVisitService implements VisitService{
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    @Autowired
    public MyVisitService(VisitRepository visitRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, ClinicRepository clinicRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.clinicRepository = clinicRepository;
    }

    public Optional<Visit> getById(long id) {
        return visitRepository.findVisitByVisitId(id);
    }

    public List<Visit> getPatientVisits(long patientId) {
        Optional<Patient> patient = patientRepository.findByPatientId(patientId);
        if (patient.isPresent()) return visitRepository.findVisitsByPatient(patient.get());
        else return new ArrayList<>();
    }

    public List<Visit> getDoctorVisits(long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findByDoctorId(doctorId);
        if (doctor.isPresent()) return visitRepository.findVisitsByDoctor(doctor.get());
        else return new ArrayList<>();
    }

    public List<Visit> getClinicVisits(long clinicId) {
        Optional<Clinic> clinic = clinicRepository.findByClinicId(clinicId);
        if (clinic.isPresent()) return visitRepository.findVisitsByClinic(clinic.get());
        else return new ArrayList<>();
    }

    public boolean addVisit(Visit visit) {
        visitRepository.save(visit);
        return true;
    }

    public boolean editVisit(long visitId, Visit editedVisit) {
        Optional<Visit> visit = visitRepository.findVisitByVisitId(visitId);
        if (visit.isPresent()) {
            Visit visitToUpdate = visit.get();
            visitToUpdate.setPatient(editedVisit.getPatient());
            visitToUpdate.setDoctor(editedVisit.getDoctor());
            visitToUpdate.setClinic(editedVisit.getClinic());
            visitToUpdate.setDate(editedVisit.getDate());
            visitToUpdate.setOnline(editedVisit.isOnline());
            visitToUpdate.setReason(editedVisit.getReason());
            visitToUpdate.setStatus(editedVisit.getStatus());
            return true;
        }
        return false;
    }

    public boolean deleteVisit(long visitId) {
        Optional<Visit> visit = visitRepository.findVisitByVisitId(visitId);
        if (visit.isPresent()) {
            visitRepository.delete(visit.get());
            return true;
        }
        return false;
    }

    //only for testing TODO: delete
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }
}
