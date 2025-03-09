package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.Order;
import aptech.vn.backend.entity.OrderStatus;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.mapper.OrderMapper;
import aptech.vn.backend.repository.OrderRepository;
import aptech.vn.backend.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto);
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<OrderDTO> findByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .map(orderMapper::toDto);
    }

    @Override
    public List<OrderDTO> findByPatientId(Long patientId) {
        return orderRepository.findByPatient_Id(patientId)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByPaymentMethod(PaymentMethod paymentMethod) {
        return orderRepository.findByPaymentMethod(paymentMethod)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByVoucherCode(String voucherCode) {
        return orderRepository.findByVoucherCode(voucherCode)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByTotalPriceGreaterThanEqual(BigDecimal amount) {
        return orderRepository.findByTotalPriceGreaterThanEqual(amount)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
