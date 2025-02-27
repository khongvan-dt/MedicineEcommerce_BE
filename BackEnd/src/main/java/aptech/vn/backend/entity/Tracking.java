package aptech.vn.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Tracking extends BaseEntity {
    @ManyToOne
    private Order order;
    private String location;
    private String message;
    @Enumerated(EnumType.STRING)
    private TrackingStatus status;
}