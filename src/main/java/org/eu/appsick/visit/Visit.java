package org.eu.appsick.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    private UUID visitId;
    private UUID patientId;
    private UUID doctorId;
    private UUID clinicId;
    private LocalDateTime date;
    private boolean online;
    private String reason;
    private VisitStatus status;

    public enum VisitStatus {
        PENDING,
        MISSED,
        MOVED,
        COMPLETED;
    }
}
