package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    @OneToOne
    private User user;
    private String experience;
    private String specialization;
    private String workplace;
    private BigDecimal accountBalance;
}