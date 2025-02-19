package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "patient_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class PatientProfile extends BaseEntity {
    @OneToOne
    private User user;
    private String bloodType;
    private String medicalHistory;
    private String allergies;
    private BigDecimal accountBalance;
}