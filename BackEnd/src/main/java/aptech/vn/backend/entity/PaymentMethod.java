package aptech.vn.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    PAYPAL, BANK_TRANSFER, CASH
}
