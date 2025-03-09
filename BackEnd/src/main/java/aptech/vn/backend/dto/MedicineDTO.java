package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {
    private Long id;
    private String code;
    private String name;
    private Long brandId;
    private String origin;
    private String manufacturer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}