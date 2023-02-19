package org.eu.appsick.medicaldata;

import org.eu.appsick.user.User;
import org.eu.appsick.user.UserService;
import org.eu.appsick.visit.Visit;
import org.eu.appsick.visit.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/medical_data")
@CrossOrigin(origins = {"http://localhost:3000/", "https://appsick.eu.org"}, allowedHeaders = "*", allowCredentials = "true")
public class MedicalDataController {

    private final MedicalDataService medicalDataService;
    private final UserService userService;
    private final VisitService visitService;

    @Autowired
    public MedicalDataController(MedicalDataService medicalDataService, UserService userService, VisitService visitService) {
        this.medicalDataService = medicalDataService;
        this.userService = userService;
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    public MedicalData getMedicalDataById(@PathVariable Long id) {
        Optional<MedicalData> medicalData = medicalDataService.getMedicalDataById(id);
        return medicalData.orElse(null);
    }

    @GetMapping("/user/{userId}")
    public Set<MedicalData> getMedicalDataByUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) return null;
        Optional<Set<MedicalData>> medicalData = medicalDataService.getMedicalDataByUser(user.get());
        return medicalData.orElse(null);
    }

    @GetMapping("/visit/{visitId}")
    public Set<MedicalData> getMedicalDataByVisit(@PathVariable Long visitId) {
        Optional<Visit> visit = visitService.getVisitById(visitId);
        if (visit.isEmpty()) return null;
        Optional<Set<MedicalData>> medicalData = medicalDataService.getMedicalDataByVisit(visit.get());
        return medicalData.orElse(null);
    }

    @PostMapping()
    public void saveMedicalData(@RequestBody MedicalDataDTO medicalData) {
        medicalDataService.saveMedicalData(
                medicalData,
                userService.getUserById((long) medicalData.getUserId()).orElse(null),
                visitService.getVisitById((long) medicalData.getVisitId()).orElse(null)
        );
    }
}
