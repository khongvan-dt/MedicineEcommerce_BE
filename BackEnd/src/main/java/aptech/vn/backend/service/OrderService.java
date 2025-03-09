package aptech.vn.backend.service;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> findAll();
    Optional<OrderDTO> findById(Long id);
    OrderDTO save(OrderDTO orderDTO);
    void deleteById(Long id);
    Optional<OrderDTO> findByOrderCode(String orderCode);
    List<OrderDTO> findByPatientId(Long patientId);
    List<OrderDTO> findByStatus(OrderStatus status);
    List<OrderDTO> findByPaymentMethod(PaymentMethod paymentMethod);
    List<OrderDTO> findByVoucherCode(String voucherCode);
    List<OrderDTO> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<OrderDTO> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}