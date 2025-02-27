package aptech.vn.backend.service;

import aptech.vn.backend.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    OrderDetail save(OrderDetail orderDetail);
    Optional<OrderDetail> findById(Long id);
    List<OrderDetail> findAll();
    Page<OrderDetail> findAll(Pageable pageable);
    void deleteById(Long id);
    List<OrderDetail> findByOrderId(Long orderId);
    List<OrderDetail> findByMedicineId(Long medicineId);
    Integer getTotalQuantityByMedicineId(Long medicineId);
}