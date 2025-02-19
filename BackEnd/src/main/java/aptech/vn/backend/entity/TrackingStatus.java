package aptech.vn.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrackingStatus {
    PENDING, IN_TRANSIT, DELIVERED, CANCELLED
}
