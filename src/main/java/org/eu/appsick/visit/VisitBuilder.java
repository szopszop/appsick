package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.medicaldata.MedicalData;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.visit.chat.ChatMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class VisitBuilder {

    private final Visit visit;

    public VisitBuilder(){
        this.visit = new Visit();
    }

    public VisitBuilder setVisitId(Long id){
        visit.setVisitId(id);
        return this;
    }

    public VisitBuilder setDate(LocalDateTime date){
        visit.setDate(date);
        return this;
    }
    public VisitBuilder setClinic(Clinic clinic){
        visit.setClinic(clinic);
        return this;
    }
    public VisitBuilder setDoctor(Doctor doctor){
        visit.setDoctor(doctor);
        return this;
    }
    public VisitBuilder setPatient(Patient patient){
        visit.setPatient(patient);
        return this;
    }
    public VisitBuilder setReason(String reason){
        visit.setReason(reason);
        return this;
    }
    public VisitBuilder setOnline(boolean online){
        visit.setOnline(online);
        return this;
    }
    public VisitBuilder setStatus(Visit.VisitStatus status){
        visit.setStatus(status);
        return this;
    }
    public VisitBuilder setChatMessageHistory(List<ChatMessage> history){
        visit.setChatMessageHistory(history);
        return this;
    }
    public VisitBuilder setDoctorSpeciality(Doctor.Speciality speciality){
        visit.setDoctorSpeciality(speciality);
        return this;
    }
    public VisitBuilder setMedicalDataSet(Set<MedicalData> medicalDataSet){
        visit.setMedicalDataSet(medicalDataSet);
        return this;
    }
    public Visit build(){
        return this.visit;
    }
}
