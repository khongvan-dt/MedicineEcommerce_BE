package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "doctor_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class DoctorService extends BaseEntity {
    @ManyToOne
    private Service service;
    @ManyToOne
    private DoctorProfile doctor;
}
