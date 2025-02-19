package aptech.vn.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConsultationStatus {
    PENDING, IN_PROGRESS, COMPLETED, CANCELLED
}
