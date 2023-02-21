package org.eu.appsick.visit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.medicaldata.MedicalData;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.visit.chat.ChatMessage;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long visitId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @Enumerated
    @JoinTable(name = "medical_specialities")
    private Doctor.Speciality doctorSpeciality;

    private LocalDateTime date;
    private boolean online;
    private String reason;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visit")
    @ToString.Exclude
    private List<ChatMessage> chatMessageHistory = new ArrayList<>();

    @Enumerated
    private VisitStatus status;
    @Enumerated
    @ElementCollection
    @JoinTable(name = "visit_types")
    private List<VisitType> visitTypes;

    @JsonIgnore
    @OneToMany(mappedBy = "visit")
    @ToString.Exclude
    private Set<MedicalData> medicalDataSet;

    public enum VisitStatus {
        PENDING("PENDING"), MISSED("MISSED"), MOVED("MOVED"), COMPLETED("COMPLETED"), UNKNOWN("UNKNOWN");

        private final String status;

        VisitStatus(String visitStatus) {
            this.status = visitStatus;
        }

        public static VisitStatus fromValue(String value) {
            for (VisitStatus visitStatus : values()) {
                if (visitStatus.status.equals(value)) {
                    return visitStatus;
                }
            }
            return UNKNOWN;
        }

    }

    public enum VisitType {
        LOCAL, ONLINE, EXAMINATION, PRESCRIPTIONS
    }
}
