package org.eu.appsick.clinic;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.visit.Visit;
import java.util.List;

public class ClinicBuilder {

    private final Clinic clinic;

    public ClinicBuilder(){
        this.clinic = new Clinic();
    }

    public ClinicBuilder setClinicId(Long id){
        this.clinic.setClinicId(id);
        return this;
    }
    public ClinicBuilder setClinicName(String name){
        this.clinic.setClinicName(name);
        return this;
    }
    public ClinicBuilder setVisists(List<Visit> visits){
        this.clinic.setVisits(visits);
        return this;
    }
    public ClinicBuilder setLatitude(String latitude){
        this.clinic.setLatitude(latitude);
        return this;
    }
    public ClinicBuilder setLongitude(String longitude){
        this.clinic.setLongitude(longitude);
        return this;
    }
    public ClinicBuilder setAvailableDoctors(List<Doctor> doctors){
        this.clinic.setAvailableDoctors(doctors);
        return this;
    }

    public Clinic build(){
        return this.clinic;
    }


}
