package aptech.vn.backend.repository;

import aptech.vn.backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
    List<OrderDetail> findByMedicineId(Long medicineId);
    List<OrderDetail> findByOrderIdAndMedicineId(Long orderId, Long medicineId);
}