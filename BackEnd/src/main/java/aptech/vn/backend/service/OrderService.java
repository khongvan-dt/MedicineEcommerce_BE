package aptech.vn.backend.service;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO.GetDto> findAll();
    Optional<OrderDTO.GetDto> findById(Long id);
    OrderDTO.GetDto saveOrUpdate(OrderDTO.SaveDto orderDTO);
    void deleteById(Long id);
    Optional<OrderDTO.GetDto> findByOrderCode(String orderCode);
    List<OrderDTO.GetDto> findByPatientId(Long patientId);
    List<OrderDTO.GetDto> findByStatus(OrderStatus status);
    List<OrderDTO.GetDto> findByPaymentMethod(PaymentMethod paymentMethod);
    List<OrderDTO.GetDto> findByVoucherCode(String voucherCode);
    List<OrderDTO.GetDto> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<OrderDTO.GetDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}