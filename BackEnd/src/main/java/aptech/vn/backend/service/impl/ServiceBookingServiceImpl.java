package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.*;
import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.mapper.ServiceBookingMapper;
import aptech.vn.backend.repository.PatientProfileRepository;
import aptech.vn.backend.repository.ServiceBookingRepository;
import aptech.vn.backend.repository.ServiceRepository;
import aptech.vn.backend.service.ServiceBookingService;
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
    private final ServiceRepository serviceRepository;
    private final PatientProfileRepository patientRepository;

    public ServiceBookingServiceImpl(
            ServiceBookingRepository serviceBookingRepository,
            ServiceRepository serviceRepository,
            PatientProfileRepository patientRepository,
            ServiceBookingMapper serviceBookingMapper) {
        this.serviceBookingRepository = serviceBookingRepository;
        this.serviceRepository = serviceRepository;
        this.patientRepository = patientRepository;
        this.serviceBookingMapper = serviceBookingMapper;
    }

    @Override
    public List<ServiceBookingDTO.GetDto> findAll() {
        return serviceBookingRepository.findAll()
                .stream()
                .map(serviceBookingMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceBookingDTO.GetDto> findById(Long id) {
        return serviceBookingRepository.findById(id)
                .map(serviceBookingMapper::toGetDto);
    }

    @Override
    @Transactional
    public ServiceBookingDTO.GetDto saveOrUpdate(ServiceBookingDTO.SaveDto serviceBookingDTO) {
        ServiceBooking serviceBooking;

        if (serviceBookingDTO.getId() == null || serviceBookingDTO.getId() == 0) {
            // INSERT case
            serviceBooking = new ServiceBooking();
            serviceBooking.setCreatedAt(LocalDateTime.now());
            serviceBooking.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<ServiceBooking> existingBooking = serviceBookingRepository.findById(serviceBookingDTO.getId());
            if (existingBooking.isEmpty()) {
                throw new RuntimeException("Service booking not found with ID: " + serviceBookingDTO.getId());
            }
            serviceBooking = existingBooking.get();
            serviceBooking.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý service relationship
        aptech.vn.backend.entity.Service service = serviceRepository.findById(serviceBookingDTO.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found with ID: " + serviceBookingDTO.getServiceId()));
        serviceBooking.setService(service);

        // Xử lý patient relationship
        PatientProfile patient = patientRepository.findById(serviceBookingDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + serviceBookingDTO.getPatientId()));
        serviceBooking.setPatient(patient);

        // Cập nhật các trường khác
        serviceBooking.setTotalPrice(serviceBookingDTO.getTotalPrice());
        serviceBooking.setPaymentMethod(serviceBookingDTO.getPaymentMethod());
        serviceBooking.setStatus(serviceBookingDTO.getStatus());

        ServiceBooking savedBooking = serviceBookingRepository.save(serviceBooking);
        return serviceBookingMapper.toGetDto(savedBooking);
    }

    @Override
    public void deleteById(Long id) {
        serviceBookingRepository.deleteById(id);
    }

    @Override
    public List<ServiceBookingDTO.GetDto> findByServiceId(Long serviceId) {
        return serviceBookingRepository.findByService_Id(serviceId)
                .stream()
                .map(serviceBookingMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO.GetDto> findByPatientId(Long patientId) {
        return serviceBookingRepository.findByPatient_Id(patientId)
                .stream()
                .map(serviceBookingMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO.GetDto> findByStatus(BookingStatus status) {
        return serviceBookingRepository.findByStatus(status)
                .stream()
                .map(serviceBookingMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO.GetDto> findByPaymentMethod(PaymentMethod paymentMethod) {
        return serviceBookingRepository.findByPaymentMethod(paymentMethod)
                .stream()
                .map(serviceBookingMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO.GetDto> findByTotalPriceGreaterThanEqual(BigDecimal amount) {
        return serviceBookingRepository.findByTotalPriceGreaterThanEqual(amount)
                .stream()
                .map(serviceBookingMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceBookingDTO.GetDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return serviceBookingRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(serviceBookingMapper::toGetDto)
                .collect(Collectors.toList());
    }
}