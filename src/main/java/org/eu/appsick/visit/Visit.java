package org.eu.appsick.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    private long visitId;
    private long patientId;
    private long doctorId;
    private long clinicId;
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
