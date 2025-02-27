package aptech.vn.backend.controller;

import aptech.vn.backend.entity.OrderDetail;
import aptech.vn.backend.service.OrderDetailService;
import aptech.vn.backend.service.impl.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailServiceImpl orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return ResponseEntity.ok(orderDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long id) {
        return orderDetailService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailService.findByOrderId(orderId));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByMedicineId(@PathVariable Long medicineId) {
        return ResponseEntity.ok(orderDetailService.findByMedicineId(medicineId));
    }

    @GetMapping("/medicine/{medicineId}/total-quantity")
    public ResponseEntity<Integer> getTotalQuantityByMedicineId(@PathVariable Long medicineId) {
        return ResponseEntity.ok(orderDetailService.getTotalQuantityByMedicineId(medicineId));
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.save(orderDetail), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        return orderDetailService.findById(id)
                .map(existingOrderDetail -> {
                    orderDetail.setId(id);
                    return ResponseEntity.ok(orderDetailService.save(orderDetail));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        return orderDetailService.findById(id)
                .map(orderDetail -> {
                    orderDetailService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}