package org.eu.appsick.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Visit {

    @Id
    @GeneratedValue
    private long visitId;

    private long patientId;
    private long doctorId;
    private long clinicId;
    private LocalDateTime date;
    private boolean online;
    private String reason;

    @Enumerated
    private VisitStatus status;

    public enum VisitStatus {
        PENDING,
        MISSED,
        MOVED,
        COMPLETED;
    }
}
