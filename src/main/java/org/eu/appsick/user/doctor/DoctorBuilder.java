package org.eu.appsick.user.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.User;
import org.eu.appsick.visit.Visit;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;
public class DoctorBuilder {

    private final Doctor doctor;

    public DoctorBuilder(){
        this.doctor = new Doctor();
    }

    public DoctorBuilder setDoctorId(Long id){
        this.doctor.setDoctorId(id);
        return this;
    }
    public DoctorBuilder setUser(User user){
        this.doctor.setUser(user);
        return this;
    }
    public DoctorBuilder setAbout(String about){
        this.doctor.setAbout(about);
        return this;
    }
    public DoctorBuilder setVisits(List<Visit> visits){
        this.doctor.setVisits(visits);
        return this;
    }
    public DoctorBuilder setClinics(List<Clinic> clinics){
        this.doctor.setClinics(clinics);
        return this;
    }
    public DoctorBuilder setMedicalSpecialities(List<Doctor.Speciality> specialities){
        this.doctor.setMedicalSpecialities(specialities);
        return this;
    }

    public Doctor build(){
        return this.doctor;
    }
}
