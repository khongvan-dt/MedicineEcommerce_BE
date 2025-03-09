package aptech.vn.backend.dto;

import aptech.vn.backend.entity.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineMediaDTO {
    private Long id;
    private Long medicineId;
    private MediaType mediaType;
    private String mediaUrl;
    private Boolean mainImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
