package aptech.vn.backend.service;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO.GetOrderDto> findAll();
    Optional<OrderDTO.GetOrderDto> findById(Long id);
    OrderDTO.GetOrderDto saveOrUpdate(OrderDTO.SaveOrderDto orderDTO);
    void deleteById(Long id);
    Optional<OrderDTO.GetOrderDto> findByOrderCode(String orderCode);
    List<OrderDTO.GetOrderDto> findByPatientId(Long patientId);
    List<OrderDTO.GetOrderDto> findByStatus(OrderStatus status);
    List<OrderDTO.GetOrderDto> findByPaymentMethod(PaymentMethod paymentMethod);
    List<OrderDTO.GetOrderDto> findByVoucherCode(String voucherCode);
    List<OrderDTO.GetOrderDto> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<OrderDTO.GetOrderDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}