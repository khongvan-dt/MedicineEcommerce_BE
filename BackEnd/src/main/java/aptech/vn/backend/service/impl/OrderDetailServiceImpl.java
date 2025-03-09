package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.OrderDetailDTO;
import aptech.vn.backend.entity.OrderDetail;
import aptech.vn.backend.mapper.OrderDetailMapper;
import aptech.vn.backend.repository.OrderDetailRepository;
import aptech.vn.backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public List<OrderDetailDTO> findAll() {
        return orderDetailRepository.findAll()
                .stream()
                .map(orderDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetailDTO> findById(Long id) {
        return orderDetailRepository.findById(id).map(orderDetailMapper::toDto);
    }

    @Override
    public OrderDetailDTO save(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDTO);
        orderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toDto(orderDetail);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailDTO> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrder_Id(orderId)
                .stream()
                .map(orderDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO> findByMedicineId(Long medicineId) {
        return orderDetailRepository.findByMedicine_Id(medicineId)
                .stream()
                .map(orderDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO> findByOrderIdAndMedicineId(Long orderId, Long medicineId) {
        return orderDetailRepository.findByOrder_IdAndMedicine_Id(orderId,medicineId)
                .stream()
                .map(orderDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO> findByQuantityGreaterThan(Integer quantity) {
        return orderDetailRepository.findByQuantityGreaterThan(quantity)
                .stream()
                .map(orderDetailMapper::toDto)
                .collect(Collectors.toList());
    }
}