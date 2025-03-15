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

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO.GetDto>> getAllOrders() {
        List<OrderDTO.GetDto> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO.GetDto> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDTO.GetDto> saveOrUpdateOrder(@RequestBody OrderDTO.SaveDto orderDTO) {
        OrderDTO.GetDto savedOrder = orderService.saveOrUpdate(orderDTO);
        return ResponseEntity.ok(savedOrder);
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
    public ResponseEntity<OrderDTO.GetDto> getOrderByOrderCode(@PathVariable String orderCode) {
        return orderService.findByOrderCode(orderCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<OrderDTO.GetDto>> getOrdersByPatientId(@PathVariable Long patientId) {
        List<OrderDTO.GetDto> orders = orderService.findByPatientId(patientId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<OrderDTO.GetDto>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<OrderDTO.GetDto> orders = orderService.findByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-payment-method/{paymentMethod}")
    public ResponseEntity<List<OrderDTO.GetDto>> getOrdersByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
        List<OrderDTO.GetDto> orders = orderService.findByPaymentMethod(paymentMethod);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-voucher/{voucherCode}")
    public ResponseEntity<List<OrderDTO.GetDto>> getOrdersByVoucherCode(@PathVariable String voucherCode) {
        List<OrderDTO.GetDto> orders = orderService.findByVoucherCode(voucherCode);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-total-price/{amount}")
    public ResponseEntity<List<OrderDTO.GetDto>> getOrdersByTotalPrice(@PathVariable BigDecimal amount) {
        List<OrderDTO.GetDto> orders = orderService.findByTotalPriceGreaterThanEqual(amount);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<OrderDTO.GetDto>> getOrdersByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<OrderDTO.GetDto> orders = orderService.findByCreatedBetween(start, end);
        return ResponseEntity.ok(orders);
    }
}