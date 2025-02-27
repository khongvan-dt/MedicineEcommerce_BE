package aptech.vn.backend.service;

import aptech.vn.backend.entity.Order;
import aptech.vn.backend.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    Page<Order> findAll(Pageable pageable);
    void deleteById(Long id);
    Optional<Order> findByOrderCode(String orderCode);
    List<Order> findByPatientId(Long patientId);
    List<Order> findByStatus(OrderStatus status);
    boolean updateStatus(Long orderId, OrderStatus newStatus);
    BigDecimal calculateOrderTotal(Long orderId);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    void applyVoucher(Long orderId, String voucherCode);
}