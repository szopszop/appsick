package org.eu.appsick.clinic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.visit.Visit;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long clinicId;
    private String clinicName;
    private String longitude;
    private String latitude;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "clinic"
    )
    private List<Visit> visits = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doctors_in_clinic")
    private List<Doctor> doctors = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Clinic clinic = (Clinic) o;
        return clinicId != null && Objects.equals(clinicId, clinic.clinicId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
