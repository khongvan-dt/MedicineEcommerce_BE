package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.ServiceBooking;
import aptech.vn.backend.repository.ServiceBookingRepository;
import aptech.vn.backend.service.ServiceBookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceBookingServiceImpl implements ServiceBookingService {

    private final ServiceBookingRepository serviceBookingRepository;

    public ServiceBookingServiceImpl(ServiceBookingRepository serviceBookingRepository) {
        this.serviceBookingRepository = serviceBookingRepository;
    }

    @Override
    public ServiceBooking save(ServiceBooking serviceBooking) {
        return serviceBookingRepository.save(serviceBooking);
    }

    @Override
    public Optional<ServiceBooking> findById(Long id) {
        return serviceBookingRepository.findById(id);
    }

    @Override
    public List<ServiceBooking> findAll() {
        return serviceBookingRepository.findAll();
    }

    @Override
    public Page<ServiceBooking> findAll(Pageable pageable) {
        return serviceBookingRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        serviceBookingRepository.deleteById(id);
    }

    @Override
    public List<ServiceBooking> findByPatientId(Long patientId) {
        return serviceBookingRepository.findByPatientId(patientId);
    }

    @Override
    public List<ServiceBooking> findByServiceId(Long serviceId) {
        return serviceBookingRepository.findByServiceId(serviceId);
    }

    @Override
    public List<ServiceBooking> findByStatus(BookingStatus status) {
        return serviceBookingRepository.findByStatus(status);
    }

    @Override
    public boolean updateStatus(Long bookingId, BookingStatus newStatus) {
        return false;
    }

    @Override
    public List<ServiceBooking> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return serviceBookingRepository.findByCreatedAtBetween(start, end);
    }
}