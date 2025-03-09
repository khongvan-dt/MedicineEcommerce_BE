package aptech.vn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private LocalDateTime lastLogin;
    private String password;
    private Integer countLock;
    private Boolean enabled;
    private Boolean locked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}