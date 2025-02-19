package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Consultation extends BaseEntity {
    @ManyToOne
    private PatientProfile patient;
    @ManyToOne
    private DoctorProfile doctor;
    private String consultationLink;
    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;
}

