package aptech.vn.backend.dto;

import aptech.vn.backend.entity.TrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingDTO {
    private Long id;
    private Long orderId;
    private String location;
    private String message;
    private TrackingStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
