package org.eu.appsick.user.patient;

import org.eu.appsick.payload.request.RegisterRequest;
import org.eu.appsick.payload.response.MessageResponse;
import org.eu.appsick.user.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PatientService {

    Optional<Patient> getPatientById(Long patientId);
    ResponseEntity<MessageResponse> addNewPatient(RegisterRequest registerRequest);
    Optional<Patient> getPatientByUserId(Long userId);
}
