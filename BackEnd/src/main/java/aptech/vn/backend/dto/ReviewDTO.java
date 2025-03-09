package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long userId;
    private Integer rating;
    private String comment;
    private Long doctorId;
    private Long medicineId;
    private Long serviceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
