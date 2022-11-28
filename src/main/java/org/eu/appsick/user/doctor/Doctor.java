package org.eu.appsick.user.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        ALLERGOLOGY,
        ANESTHESIOLOGY_AND_INTENSIVE_CARE,
        ANGIOLOGY,
        AUDIOLOGY_AND_PHONIATRICS,
        BALNEOLOGY_AND_PHYSICAL_MEDICINE,
        CHILDRENS_SURGERY,
        CHINESE_SURGERY,
        VASCULAR_SURGERY,
        GENERAL_SURGERY,
        ONCOLOGICAL_SURGERY,
        COSMETIC_SURGERY,
        MAXILLOFACIAL_SURGERY,
        LUNG_DISEASES,
        CHILDRENS_LUNG_DISEASES,
        INTERNAL_DISEASES,
        INFECTIOUS_DISEASES,
        DERMATOLOGY_AND_VENEREOLOGY,
        DIABETOLOGY,
        LABORATORY_DIAGNOSIS,
        ENDOCRINOLOGY,
        GYNECOLOGICAL_ENDOCRINOLOGY_AND_REPRODUCTION,
        ENDOCRINOLOGY_AND_PEDIATRIC_DIABETOLOGY,
        EPIDEMIOLOGY,
        CLINICAL_PHARMACOLOGY,
        GASTROENTEROLOGY,
        PEDIATRIC_GASTROENTEROLOGY,
        CLINICAL_GENETICS,
        GERIATRICS,
        ONCOLOGICAL_GYNECOLOGY,
        HEMATOLOGY,
        HYPERTENSIOLOGY,
        CLINICAL_IMMUNOLOGY,
        INTENSIVE_CARE,
        CARDIAC_SURGERY,
        CARDIOLOGY,
        CHILDRENS_CARDIOLOGY,
        AVIATION_MEDICINE,
        MARINE_AND_TROPICAL_MEDICINE,
        NUCLEAR_MEDICINE,
        PALLIATIVE_MEDICINE,
        OCCUPATIONAL_MEDICINE,
        EMERGENCY_MEDICINE,
        FAMILY_MEDICINE,
        FORENSIC_MEDICINE,
        SPORTS_MEDICINE,
        MEDICAL_MICROBIOLOGY,
        NEPHROLOGY,
        CHILDRENS_NEPHROLOGY,
        NEONATOLOGY,
        NEUROSURGERY,
        NEUROLOGY,
        CHILD_NEUROLOGY,
        NEUROPATHOLOGY,
        OPHTHALMOLOGIST,
        PEDIATRIC_ONCOLOGY_AND_HEMATOLOGY,
        CLINICAL_ONCOLOGY,
        ORTHOPEDICS_AND_TRAUMATOLOGY_OF_THE_MUSCULOSKELETAL_SYSTEM,
        OTORHINOLARYNGOLOGY,
        PEDIATRIC_OTORHINOLARYNGOLOGY,
        PATHOMORPHOLOGY,
        PEDIATRICS,
        PEDIATRIC_METABOLISM,
        PERINATOLOGY,
        OBSTETRICS_AND_GYNECOLOGY,
        PSYCHIATRY,
        PSYCHIATRY_OF_CHILDREN_AND_ADOLESCENTS,
        RADIOLOGY_AND_DIAGNOSTIC_IMAGING,
        ONCOLOGICAL_RADIOTHERAPY,
        MEDICAL_REHABILITATION,
        RHEUMATOLOGY,
        SEXOLOGY,
        CLINICAL_TOXICOLOGY,
        CLINICAL_TRANSFUSION_MEDICINE,
        CLINICAL_TRANSPLANTOLOGY,
        UROLOGY,
        CHILDRENS_UROLOGY,
        PUBLIC_HEALTH
    }

}
