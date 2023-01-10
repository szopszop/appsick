package org.eu.appsick.user.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.eu.appsick.user.User;
import org.eu.appsick.visit.Visit;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@ToString
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

        private final String specialityName;

        Speciality(String specialityName) {
            this.specialityName = specialityName;
        }

        public static Speciality fromValue(String value) {
            for (Speciality speciality : values()) {
                if (speciality.specialityName.equals(value)) {
                    return speciality;
                }
            }
            throw new IllegalArgumentException("Unknown enum type");
        }


        @JsonValue
        @Override
        public String toString() {
            return specialityName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Doctor doctor = (Doctor) o;
        return doctorId != null && Objects.equals(doctorId, doctor.doctorId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
