package org.eu.appsick.user.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.appsick.user.User;
import org.eu.appsick.visit.Visit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long doctorId;
    private String about;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated
    @ElementCollection
    @JoinTable(name = "medical_specialities")
    private List<Speciality> medicalSpecialities;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "doctor"
    )
    private List<Visit> visits = new ArrayList<>();

    public enum Speciality {

        ALLERGOLOGY("Allergology"),
        ANESTHESIOLOGY_AND_INTENSIVE_CARE("Anesthesiology and intensive care"),
        GENERAL_SURGERY("General surgery"),
        LUNG_DISEASES("Lung diseases"),
        INTERNAL_DISEASES("Internal diseases"),
        INFECTIOUS_DISEASES("Infectious diseases"),
        DERMATOLOGY_AND_VENEREOLOGY("Dermatology and venereology"),
        DIABETOLOGY("Diabetology"),
        ENDOCRINOLOGY("Endocrinology"),
        GERIATRICS("Geriatrics"),
        INTENSIVE_CARE("Intensive care"),
        CARDIOLOGY("Cardiology"),
        PALLIATIVE_MEDICINE("Palliative medicine"),
        FAMILY_MEDICINE("Family medicine"),
        SPORTS_MEDICINE("Sports medicine"),
        NEUROLOGY("Neurology"),
        ONCOLOGY("Oncology"),
        PEDIATRICS("Pediatrics"),
        GYNECOLOGY("Gynecology"),
        PSYCHIATRY("Psychiatry"),
        RHEUMATOLOGY("Rheumatology"),
        SEXOLOGY("Sexology"),
        UROLOGY("Urology");

        private String speciality;

        Speciality(String speciality) {
            this.speciality = speciality;
        }

        @JsonValue
        @Override
        public String toString() {
            return speciality;
        }
    }

}
