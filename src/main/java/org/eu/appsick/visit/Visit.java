package org.eu.appsick.visit;

import lombok.*;
import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.visit.chat.ChatMessage;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter

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

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "visit"
    )
    @ToString.Exclude
    private List<ChatMessage> chatMessageHistory = new ArrayList<>();

    @Enumerated
    private VisitStatus status;

    public enum VisitStatus {
        PENDING("PENDING"),
        MISSED("MISSED"),
        MOVED("MOVED"),
        COMPLETED("COMPLETED");

        private final String visitStatus;

        VisitStatus(String visitStatus) {
            this.visitStatus = visitStatus;
        }


        public static VisitStatus fromValue(String value) {
            for (VisitStatus visitStatus : values()) {
                if (visitStatus.visitStatus.equals(value)) {
                    return visitStatus;
                }
            }
            System.out.println(value + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            throw new IllegalArgumentException("Unknown enum type");
        }

    }

    @Enumerated
    @ElementCollection
    @JoinTable(name = "visit_types")
    private List<VisitType> visitTypes;

    public enum VisitType {
        LOCAL,
        ONLINE,
        EXAMINATION,
        PRESCRIPTIONS
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Visit visit = (Visit) o;
        return visitId != null && Objects.equals(visitId, visit.visitId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
