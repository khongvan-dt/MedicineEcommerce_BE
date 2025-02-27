package aptech.vn.backend.repository;

import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.ServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long> {
    List<ServiceBooking> findByServiceId(Long serviceId);
    List<ServiceBooking> findByPatientId(Long patientId);
    List<ServiceBooking> findByStatus(BookingStatus status);
    List<ServiceBooking> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<ServiceBooking> findByPatientIdAndStatus(Long patientId, BookingStatus status);
}