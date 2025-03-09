package aptech.vn.backend.controller;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Optional<OrderDTO> order = orderService.findById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.save(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        if (!orderService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        OrderDTO updatedOrder = orderService.save(orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-order-code/{orderCode}")
    public ResponseEntity<OrderDTO> getOrderByOrderCode(@PathVariable String orderCode) {
        Optional<OrderDTO> order = orderService.findByOrderCode(orderCode);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByPatientId(@PathVariable Long patientId) {
        List<OrderDTO> orders = orderService.findByPatientId(patientId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<OrderDTO> orders = orderService.findByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-payment-method/{paymentMethod}")
    public ResponseEntity<List<OrderDTO>> getOrdersByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
        List<OrderDTO> orders = orderService.findByPaymentMethod(paymentMethod);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-voucher/{voucherCode}")
    public ResponseEntity<List<OrderDTO>> getOrdersByVoucherCode(@PathVariable String voucherCode) {
        List<OrderDTO> orders = orderService.findByVoucherCode(voucherCode);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-total-price/{amount}")
    public ResponseEntity<List<OrderDTO>> getOrdersByTotalPrice(@PathVariable BigDecimal amount) {
        List<OrderDTO> orders = orderService.findByTotalPriceGreaterThanEqual(amount);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<OrderDTO>> getOrdersByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<OrderDTO> orders = orderService.findByCreatedBetween(start, end);
        return ResponseEntity.ok(orders);
    }
}
