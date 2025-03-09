package aptech.vn.backend.repository;

import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.entity.ServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long> {
    List<ServiceBooking> findByService_Id(Long serviceId);
    List<ServiceBooking> findByPatient_Id(Long patientId);
    List<ServiceBooking> findByStatus(BookingStatus status);
    List<ServiceBooking> findByPaymentMethod(PaymentMethod paymentMethod);
    List<ServiceBooking> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<ServiceBooking> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}