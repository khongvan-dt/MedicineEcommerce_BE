package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.ServiceBooking;
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

    private final ServiceBookingServiceImpl serviceBookingServiceImpl;

    public ServiceBookingServiceImpl() {
        serviceBookingServiceImpl = new ServiceBookingServiceImpl();
    }

    @Override
    public ServiceBooking save(ServiceBooking serviceBooking) {
        return serviceBookingServiceImpl.save(serviceBooking);
    }

    @Override
    public Optional<ServiceBooking> findById(Long id) {
        return serviceBookingServiceImpl.findById(id);
    }

    @Override
    public List<ServiceBooking> findAll() {
        return serviceBookingServiceImpl.findAll();
    }

    @Override
    public Page<ServiceBooking> findAll(Pageable pageable) {
        return serviceBookingServiceImpl.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        serviceBookingServiceImpl.deleteById(id);
    }

    @Override
    public List<ServiceBooking> findByPatientId(Long patientId) {
        return serviceBookingServiceImpl.findByPatientId(patientId);
    }

    @Override
    public List<ServiceBooking> findByServiceId(Long serviceId) {
        return serviceBookingServiceImpl.findByServiceId(serviceId);
    }

    @Override
    public List<ServiceBooking> findByStatus(BookingStatus status) {
        return serviceBookingServiceImpl.findByStatus(status);
    }

    @Override
    public boolean updateStatus(Long bookingId, BookingStatus newStatus) {
        return serviceBookingServiceImpl.updateStatus(bookingId, newStatus);
    }

    @Override
    public List<ServiceBooking> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return serviceBookingServiceImpl.findByCreatedAtBetween(start, end);
    }
}
