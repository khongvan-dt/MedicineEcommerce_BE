package aptech.vn.backend.controller;

import aptech.vn.backend.dto.OrderDetailDTO;
import aptech.vn.backend.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin("*")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails() {
        List<OrderDetailDTO> orderDetails = orderDetailService.findAll();
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetailDTO> orderDetail = orderDetailService.findById(id);
        return orderDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO savedOrderDetail = orderDetailService.save(orderDetailDTO);
        return ResponseEntity.ok(savedOrderDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
        if (!orderDetailService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        OrderDetailDTO updatedOrderDetail = orderDetailService.save(orderDetailDTO);
        return ResponseEntity.ok(updatedOrderDetail);
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
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.findByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByMedicineId(@PathVariable Long medicineId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.findByMedicineId(medicineId);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/by-order-and-medicine")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByOrderAndMedicine(
            @RequestParam Long orderId,
            @RequestParam Long medicineId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.findByOrderIdAndMedicineId(orderId, medicineId);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/by-quantity-greater-than/{quantity}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByQuantityGreaterThan(@PathVariable Integer quantity) {
        List<OrderDetailDTO> orderDetails = orderDetailService.findByQuantityGreaterThan(quantity);
        return ResponseEntity.ok(orderDetails);
    }
}
