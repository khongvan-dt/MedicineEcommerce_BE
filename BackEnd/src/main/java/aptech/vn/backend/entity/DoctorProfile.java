package aptech.vn.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "doctor_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class DoctorProfile extends BaseEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "experience", nullable = true, columnDefinition = "TEXT")
    private String experience;

    @Column(name = "specialization", nullable = true)
    private String specialization;

    @Column(name = "workplace", nullable = true)
    private String workplace;

    @Column(name = "account_balance", nullable = true)
    private BigDecimal accountBalance;
}