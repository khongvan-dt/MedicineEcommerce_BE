package aptech.vn.backend.service;

import aptech.vn.backend.dto.OrderDetailDTO;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetailDTO> findAll();
    Optional<OrderDetailDTO> findById(Long id);
    OrderDetailDTO save(OrderDetailDTO orderDetailDTO);
    void deleteById(Long id);
    List<OrderDetailDTO> findByOrderId(Long orderId);
    List<OrderDetailDTO> findByMedicineId(Long medicineId);
    List<OrderDetailDTO> findByOrderIdAndMedicineId(Long orderId, Long medicineId);
    List<OrderDetailDTO> findByQuantityGreaterThan(Integer quantity);
}