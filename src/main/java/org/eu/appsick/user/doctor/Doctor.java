package org.eu.appsick.user.doctor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.eu.appsick.user.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User {

    private UUID doctorId;
    private String about;
    private List<Speciality> medicalSpecialities;

    public Doctor(UUID userId, String firstName, String lastName, LocalDate birthDate, Sex sex, String telephoneNumber, String email, String password, String about) {
        super(userId, firstName, lastName, birthDate, sex, telephoneNumber, email, password);
        this.about = about;
        this.medicalSpecialities = new ArrayList<>();
    }

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
