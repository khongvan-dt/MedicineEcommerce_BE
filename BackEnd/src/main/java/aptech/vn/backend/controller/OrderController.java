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
    public ResponseEntity<List<OrderDTO.GetOrderDto>> getAllOrders() {
        List<OrderDTO.GetOrderDto> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO.GetOrderDto> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDTO.GetOrderDto> saveOrUpdateOrder(@RequestBody OrderDTO.SaveOrderDto orderDTO) {
        OrderDTO.GetOrderDto savedOrder = orderService.saveOrUpdate(orderDTO);
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
    public ResponseEntity<OrderDTO.GetOrderDto> getOrderByOrderCode(@PathVariable String orderCode) {
        return orderService.findByOrderCode(orderCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<OrderDTO.GetOrderDto>> getOrdersByPatientId(@PathVariable Long patientId) {
        List<OrderDTO.GetOrderDto> orders = orderService.findByPatientId(patientId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<OrderDTO.GetOrderDto>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<OrderDTO.GetOrderDto> orders = orderService.findByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-payment-method/{paymentMethod}")
    public ResponseEntity<List<OrderDTO.GetOrderDto>> getOrdersByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
        List<OrderDTO.GetOrderDto> orders = orderService.findByPaymentMethod(paymentMethod);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-voucher/{voucherCode}")
    public ResponseEntity<List<OrderDTO.GetOrderDto>> getOrdersByVoucherCode(@PathVariable String voucherCode) {
        List<OrderDTO.GetOrderDto> orders = orderService.findByVoucherCode(voucherCode);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-total-price/{amount}")
    public ResponseEntity<List<OrderDTO.GetOrderDto>> getOrdersByTotalPrice(@PathVariable BigDecimal amount) {
        List<OrderDTO.GetOrderDto> orders = orderService.findByTotalPriceGreaterThanEqual(amount);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<OrderDTO.GetOrderDto>> getOrdersByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<OrderDTO.GetOrderDto> orders = orderService.findByCreatedBetween(start, end);
        return ResponseEntity.ok(orders);
    }
}