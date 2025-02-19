package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Appointment extends BaseEntity {
    @ManyToOne
    private PatientProfile patient;
    @ManyToOne
    private DoctorProfile doctor;
    private LocalDateTime appointmentDate;
    private LocalTime appointmentTime;
}
