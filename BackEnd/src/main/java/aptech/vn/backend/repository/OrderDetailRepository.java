package aptech.vn.backend.repository;

import aptech.vn.backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder_Id(Long orderId);
    List<OrderDetail> findByMedicine_Id(Long medicineId);
    List<OrderDetail> findByOrder_IdAndMedicine_Id(Long orderId, Long medicineId);
    List<OrderDetail> findByQuantityGreaterThan(Integer quantity);
}