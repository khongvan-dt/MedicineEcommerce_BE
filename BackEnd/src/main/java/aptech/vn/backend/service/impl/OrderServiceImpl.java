package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.Order;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.mapper.OrderMapper;
import aptech.vn.backend.repository.OrderRepository;
import aptech.vn.backend.repository.PatientProfileRepository;
import aptech.vn.backend.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PatientProfileRepository patientRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            PatientProfileRepository patientRepository,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.patientRepository = patientRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO.GetDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO.GetDto> findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toGetDto);
    }

    @Override
    public OrderDTO.GetDto saveOrUpdate(OrderDTO.SaveDto orderDTO) {
        Order order;

        if (orderDTO.getId() == null || orderDTO.getId() == 0) {
            // INSERT case
            order = new Order();
            order.setCreatedAt(LocalDateTime.now());
            order.setUpdatedAt(LocalDateTime.now());

            // Tạo mã đơn hàng nếu chưa có
            if (orderDTO.getOrderCode() == null || orderDTO.getOrderCode().isEmpty()) {
                order.setOrderCode(generateOrderCode());
            } else {
                order.setOrderCode(orderDTO.getOrderCode());
            }
        } else {
            // UPDATE case
            Optional<Order> existingOrder = orderRepository.findById(orderDTO.getId());
            if (existingOrder.isEmpty()) {
                throw new RuntimeException("Order not found with ID: " + orderDTO.getId());
            }
            order = existingOrder.get();
            order.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý patient relationship
        if (orderDTO.getPatientId() != null) {
            PatientProfile patient = patientRepository.findById(orderDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + orderDTO.getPatientId()));
            order.setPatient(patient);
        }

        // Cập nhật các trường khác
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setStatus(orderDTO.getStatus());
        order.setVoucherCode(orderDTO.getVoucherCode());
        order.setDiscountAmount(orderDTO.getDiscountAmount());
        order.setNote(orderDTO.getNote());

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toGetDto(savedOrder);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<OrderDTO.GetDto> findByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .map(orderMapper::toGetDto);
    }

    @Override
    public List<OrderDTO.GetDto> findByPatientId(Long patientId) {
        return orderRepository.findByPatient_Id(patientId)
                .stream()
                .map(orderMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO.GetDto> findByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(orderMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO.GetDto> findByPaymentMethod(PaymentMethod paymentMethod) {
        return orderRepository.findByPaymentMethod(paymentMethod)
                .stream()
                .map(orderMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO.GetDto> findByVoucherCode(String voucherCode) {
        return orderRepository.findByVoucherCode(voucherCode)
                .stream()
                .map(orderMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO.GetDto> findByTotalPriceGreaterThanEqual(BigDecimal amount) {
        return orderRepository.findByTotalPriceGreaterThanEqual(amount)
                .stream()
                .map(orderMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO.GetDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(orderMapper::toGetDto)
                .collect(Collectors.toList());
    }

    // Hàm tiện ích để tạo mã đơn hàng
    private String generateOrderCode() {
        String prefix = "ORD";
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(5);
        return prefix + timestamp;
    }
}