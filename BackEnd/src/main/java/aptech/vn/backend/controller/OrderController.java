package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Order;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.service.OrderService;
import aptech.vn.backend.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{orderCode}")
    public ResponseEntity<Order> getOrderByCode(@PathVariable String orderCode) {
        return orderService.findByOrderCode(orderCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Order>> getOrdersByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(orderService.findByPatientId(patientId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus status) {
        return ResponseEntity.ok(orderService.findByStatus(status));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Order>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(orderService.findByCreatedAtBetween(start, end));
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> getOrderTotal(@PathVariable Long id) {
        return orderService.findById(id)
                .map(order -> ResponseEntity.ok(orderService.calculateOrderTotal(id)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.findById(id)
                .map(existingOrder -> {
                    order.setId(id);
                    return ResponseEntity.ok(orderService.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, OrderStatus> statusMap) {

        OrderStatus newStatus = statusMap.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().body("Status field is required");
        }

        return orderService.findById(id)
                .map(existingOrder -> {
                    boolean updated = orderService.updateStatus(id, newStatus);
                    if (updated) {
                        return ResponseEntity.ok("Order status updated successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to update order status");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/apply-voucher")
    public ResponseEntity<String> applyVoucher(
            @PathVariable Long id,
            @RequestBody Map<String, String> voucherMap) {

        String voucherCode = voucherMap.get("voucherCode");
        if (voucherCode == null || voucherCode.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Voucher code is required");
        }

        return orderService.findById(id)
                .map(existingOrder -> {
                    orderService.applyVoucher(id, voucherCode);
                    return ResponseEntity.ok("Voucher applied successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return orderService.findById(id)
                .map(order -> {
                    orderService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}