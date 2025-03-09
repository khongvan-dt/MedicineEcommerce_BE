package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {
    private Long id;
    private Long userId;
    private Long roleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
