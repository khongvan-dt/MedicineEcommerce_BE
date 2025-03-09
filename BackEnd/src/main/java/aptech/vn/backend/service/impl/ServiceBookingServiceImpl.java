package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.entity.ServiceBooking;
import aptech.vn.backend.mapper.ServiceBookingMapper;
import aptech.vn.backend.repository.ServiceBookingRepository;
import aptech.vn.backend.service.ServiceBookingService;
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
public class ServiceBookingServiceImpl implements ServiceBookingService {
    private final ServiceBookingMapper serviceBookingMapper;
    private final ServiceBookingRepository serviceBookingRepository;

    public ServiceBookingServiceImpl(ServiceBookingRepository serviceBookingRepository, ServiceBookingMapper serviceBookingMapper) {
        this.serviceBookingRepository = serviceBookingRepository;
        this.serviceBookingMapper = serviceBookingMapper;
    }

    @Override
    public List<ServiceBookingDTO> findAll() {
        return serviceBookingRepository.findAll()
                .stream()
                .map(serviceBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceBookingDTO> findById(Long id) {
        return serviceBookingRepository.findById(id).map(serviceBookingMapper::toDto);
    }

    @Override
    public ServiceBookingDTO save(ServiceBookingDTO serviceBookingDTO) {
        ServiceBooking serviceBooking = serviceBookingMapper.toEntity(serviceBookingDTO);
        serviceBookingRepository.save(serviceBooking);
        return serviceBookingMapper.toDto(serviceBooking);
    }

    @Override
    public void deleteById(Long id) {
        serviceBookingRepository.deleteById(id);
    }

    @Override
    public List<ServiceBookingDTO> findByServiceId(Long serviceId) {
        return serviceBookingRepository.findByService_Id(serviceId)
                .stream()
                .map(serviceBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO> findByPatientId(Long patientId) {
        return serviceBookingRepository.findByPatient_Id(patientId)
                .stream()
                .map(serviceBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO> findByStatus(BookingStatus status) {
        return serviceBookingRepository.findByStatus(status)
                .stream()
                .map(serviceBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO> findByPaymentMethod(PaymentMethod paymentMethod) {
        return serviceBookingRepository.findByPaymentMethod(paymentMethod)
                .stream()
                .map(serviceBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO> findByTotalPriceGreaterThanEqual(BigDecimal amount) {
        return serviceBookingRepository.findByTotalPriceGreaterThanEqual(amount)
                .stream()
                .map(serviceBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO> findByCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return serviceBookingRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(serviceBookingMapper::toDto)
                .collect(Collectors.toList());
    }
}