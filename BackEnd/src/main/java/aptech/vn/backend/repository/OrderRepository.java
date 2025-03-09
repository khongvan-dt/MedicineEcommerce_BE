package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Order;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderCode(String orderCode);
    List<Order> findByPatient_Id(Long patientId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByPaymentMethod(PaymentMethod paymentMethod);
    List<Order> findByVoucherCode(String voucherCode);
    List<Order> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}