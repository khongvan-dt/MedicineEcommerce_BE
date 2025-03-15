package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.OrderDetailDTO;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.entity.Order;
import aptech.vn.backend.entity.OrderDetail;
import aptech.vn.backend.mapper.OrderDetailMapper;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.repository.OrderDetailRepository;
import aptech.vn.backend.repository.OrderRepository;
import aptech.vn.backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final MedicineRepository medicineRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderDetailServiceImpl(
            OrderDetailRepository orderDetailRepository,
            OrderRepository orderRepository,
            MedicineRepository medicineRepository,
            OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.medicineRepository = medicineRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public List<OrderDetailDTO.GetDto> findAll() {
        return orderDetailRepository.findAll()
                .stream()
                .map(orderDetailMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetailDTO.GetDto> findById(Long id) {
        return orderDetailRepository.findById(id)
                .map(orderDetailMapper::toGetDto);
    }

    @Override
    @Transactional
    public OrderDetailDTO.GetDto saveOrUpdate(OrderDetailDTO.SaveDto orderDetailDTO) {
        OrderDetail orderDetail;

        if (orderDetailDTO.getId() == null || orderDetailDTO.getId() == 0) {
            // INSERT case
            orderDetail = new OrderDetail();
            orderDetail.setCreatedAt(LocalDateTime.now());
            orderDetail.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(orderDetailDTO.getId());
            if (existingOrderDetail.isEmpty()) {
                throw new RuntimeException("Order detail not found with ID: " + orderDetailDTO.getId());
            }
            orderDetail = existingOrderDetail.get();
            orderDetail.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý order relationship
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderDetailDTO.getOrderId()));
        orderDetail.setOrder(order);

        // Xử lý medicine relationship
        Medicine medicine = medicineRepository.findById(orderDetailDTO.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + orderDetailDTO.getMedicineId()));
        orderDetail.setMedicine(medicine);

        // Cập nhật các trường khác
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setUnitPrice(orderDetailDTO.getUnitPrice());

        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toGetDto(savedOrderDetail);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailDTO.GetDto> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrder_Id(orderId)
                .stream()
                .map(orderDetailMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO.GetDto> findByMedicineId(Long medicineId) {
        return orderDetailRepository.findByMedicine_Id(medicineId)
                .stream()
                .map(orderDetailMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO.GetDto> findByOrderIdAndMedicineId(Long orderId, Long medicineId) {
        return orderDetailRepository.findByOrder_IdAndMedicine_Id(orderId, medicineId)
                .stream()
                .map(orderDetailMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO.GetDto> findByQuantityGreaterThan(Integer quantity) {
        return orderDetailRepository.findByQuantityGreaterThan(quantity)
                .stream()
                .map(orderDetailMapper::toGetDto)
                .collect(Collectors.toList());
    }
}