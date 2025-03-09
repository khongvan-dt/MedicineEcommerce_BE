package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServiceBookingService {
    List<ServiceBookingDTO> findAll();
    Optional<ServiceBookingDTO> findById(Long id);
    ServiceBookingDTO save(ServiceBookingDTO serviceBookingDTO);
    void deleteById(Long id);
    List<ServiceBookingDTO> findByServiceId(Long serviceId);
    List<ServiceBookingDTO> findByPatientId(Long patientId);
    List<ServiceBookingDTO> findByStatus(BookingStatus status);
    List<ServiceBookingDTO> findByPaymentMethod(PaymentMethod paymentMethod);
    List<ServiceBookingDTO> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<ServiceBookingDTO> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}