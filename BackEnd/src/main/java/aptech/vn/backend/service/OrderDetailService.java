package aptech.vn.backend.service;

import aptech.vn.backend.dto.OrderDetailDTO;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetailDTO.GetDto> findAll();
    Optional<OrderDetailDTO.GetDto> findById(Long id);
    OrderDetailDTO.GetDto saveOrUpdate(OrderDetailDTO.SaveDto orderDetailDTO);
    void deleteById(Long id);
    List<OrderDetailDTO.GetDto> findByOrderId(Long orderId);
    List<OrderDetailDTO.GetDto> findByMedicineId(Long medicineId);
    List<OrderDetailDTO.GetDto> findByOrderIdAndMedicineId(Long orderId, Long medicineId);
    List<OrderDetailDTO.GetDto> findByQuantityGreaterThan(Integer quantity);
}