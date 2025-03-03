package aptech.vn.backend.DTO;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private Long parentId;
}

