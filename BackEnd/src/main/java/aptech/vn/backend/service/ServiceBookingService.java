package aptech.vn.backend.service;

import aptech.vn.backend.entity.ServiceBooking;
import aptech.vn.backend.entity.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServiceBookingService {
    ServiceBooking save(ServiceBooking serviceBooking);
    Optional<ServiceBooking> findById(Long id);
    List<ServiceBooking> findAll();
    Page<ServiceBooking> findAll(Pageable pageable);
    void deleteById(Long id);
    List<ServiceBooking> findByPatientId(Long patientId);
    List<ServiceBooking> findByServiceId(Long serviceId);
    List<ServiceBooking> findByStatus(BookingStatus status);
    boolean updateStatus(Long bookingId, BookingStatus newStatus);
    List<ServiceBooking> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}