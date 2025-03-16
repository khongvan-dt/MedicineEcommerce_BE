package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class CategoryDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCategoryDto {
        private Long id;
        private String name;
        private Long parentId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveCategoryDto {
        private Long id;
        private String name;
        private Long parentId;
    }
}