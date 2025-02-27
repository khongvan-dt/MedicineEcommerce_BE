package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Order;
import aptech.vn.backend.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderCode(String orderCode);
    List<Order> findByPatientId(Long patientId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findByPatientIdAndStatus(Long patientId, OrderStatus status);
    List<Order> findByVoucherCode(String voucherCode);
}
