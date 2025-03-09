package aptech.vn.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "blood_type", nullable = true)
    private String bloodType;

    @Column(name = "medical_history", nullable = true, columnDefinition = "TEXT")
    private String medicalHistory;

    @Column(name = "allergies", nullable = true, columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "account_balance", nullable = true)
    private BigDecimal accountBalance;
}