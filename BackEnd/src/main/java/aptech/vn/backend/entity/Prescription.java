package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Prescription extends BaseEntity {
    @ManyToOne
    private DoctorProfile doctor;
    @ManyToOne
    private PatientProfile patient;
    @ManyToOne
    private Medicine medicine;
    private String dosage;
}
