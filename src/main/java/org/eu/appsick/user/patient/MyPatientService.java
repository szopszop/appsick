package org.eu.appsick.user.patient;

import org.eu.appsick.payload.request.RegisterRequest;
import org.eu.appsick.payload.response.MessageResponse;
import org.eu.appsick.user.User;
import org.eu.appsick.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPatientService implements PatientService{

    private final PatientRepository patientRepository;
    private final UserService userService;

    @Autowired
    public MyPatientService(PatientRepository patientRepository, UserService userService) {
        this.patientRepository = patientRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<Patient> getPatientByUserId(Long userId) {
        return patientRepository.findPatientByUserId(userId);
    }

    @Override
    public ResponseEntity<MessageResponse> addNewPatient(RegisterRequest registerRequest) {
        if (userService.isUserExistsByEmail(registerRequest)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = userService.addUser(registerRequest);
        Patient patient = new Patient(registerRequest.getPesel(), false, user);
        patientRepository.save(patient);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
