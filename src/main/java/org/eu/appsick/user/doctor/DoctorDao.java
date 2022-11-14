package org.eu.appsick.user.doctor;


import java.util.Optional;

public interface DoctorDao {

    Optional<Doctor> getDoctorById(long doctorId);
    void add(Doctor doctor);
    void remove(Doctor doctor);

}
