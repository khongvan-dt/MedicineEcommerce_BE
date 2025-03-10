package aptech.vn.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING, PROCESSING, COMPLETED, CANCELLED
}
