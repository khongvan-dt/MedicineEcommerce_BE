package aptech.vn.backend.controller;

import aptech.vn.backend.dto.OrderDetailDTO;
import aptech.vn.backend.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin("*")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO.GetDto>> getAllOrderDetails() {
        List<OrderDetailDTO.GetDto> orderDetails = orderDetailService.findAll();
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO.GetDto> getOrderDetailById(@PathVariable Long id) {
        return orderDetailService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDetailDTO.GetDto> saveOrUpdateOrderDetail(@RequestBody OrderDetailDTO.SaveDto orderDetailDTO) {
        OrderDetailDTO.GetDto savedOrderDetail = orderDetailService.saveOrUpdate(orderDetailDTO);
        return ResponseEntity.ok(savedOrderDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        if (!orderDetailService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        orderDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<OrderDetailDTO.GetDto>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetailDTO.GetDto> orderDetails = orderDetailService.findByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<OrderDetailDTO.GetDto>> getOrderDetailsByMedicineId(@PathVariable Long medicineId) {
        List<OrderDetailDTO.GetDto> orderDetails = orderDetailService.findByMedicineId(medicineId);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/by-order-and-medicine")
    public ResponseEntity<List<OrderDetailDTO.GetDto>> getOrderDetailsByOrderAndMedicine(
            @RequestParam Long orderId,
            @RequestParam Long medicineId) {
        List<OrderDetailDTO.GetDto> orderDetails = orderDetailService.findByOrderIdAndMedicineId(orderId, medicineId);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/by-quantity-greater-than/{quantity}")
    public ResponseEntity<List<OrderDetailDTO.GetDto>> getOrderDetailsByQuantityGreaterThan(@PathVariable Integer quantity) {
        List<OrderDetailDTO.GetDto> orderDetails = orderDetailService.findByQuantityGreaterThan(quantity);
        return ResponseEntity.ok(orderDetails);
    }
}