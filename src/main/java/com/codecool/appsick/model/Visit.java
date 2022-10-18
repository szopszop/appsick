package com.codecool.appsick.model;

import java.time.LocalDate;
import java.util.UUID;

public class Visit {

    private UUID visitId;
    private UUID patientId;
    private UUID doctorId;
    private UUID clinicId;
    private LocalDate date;
    private boolean online;
    private String reason;
    private VisitStatus status;

    public enum VisitStatus {
        PENDING,
        MISSED,
        MOVED,
        COMPLETED;
    }

    public Visit(UUID patientId, UUID doctorId, UUID clinicId, LocalDate date, boolean online, String reason, VisitStatus status) {
        this.visitId = UUID.randomUUID();
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.date = date;
        this.online = online;
        this.reason = reason;
        this.status = status;
    }
}
