package aptech.vn.backend.service;

import aptech.vn.backend.dto.OrderDetailDTO;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetailDTO.GetOrderDetailDto> findAll();
    Optional<OrderDetailDTO.GetOrderDetailDto> findById(Long id);
    OrderDetailDTO.GetOrderDetailDto saveOrUpdate(OrderDetailDTO.SaveOrderDetailDto orderDetailDTO);
    void deleteById(Long id);
    List<OrderDetailDTO.GetOrderDetailDto> findByOrderId(Long orderId);
    List<OrderDetailDTO.GetOrderDetailDto> findByMedicineId(Long medicineId);
    List<OrderDetailDTO.GetOrderDetailDto> findByOrderIdAndMedicineId(Long orderId, Long medicineId);
    List<OrderDetailDTO.GetOrderDetailDto> findByQuantityGreaterThan(Integer quantity);
}