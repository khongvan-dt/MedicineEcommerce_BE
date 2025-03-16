package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
public class BrandDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetBrandDto {
        private Long id;
        private String name;
        private String image;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveBrandDto {
        private Long id;
        private String name;
        private String image;
    }
}
