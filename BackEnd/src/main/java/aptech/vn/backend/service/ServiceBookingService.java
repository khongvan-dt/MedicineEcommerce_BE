package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServiceBookingService {
    List<ServiceBookingDTO.GetDto> findAll();
    Optional<ServiceBookingDTO.GetDto> findById(Long id);
    ServiceBookingDTO.GetDto saveOrUpdate(ServiceBookingDTO.SaveDto serviceBookingDTO);
    void deleteById(Long id);
    List<ServiceBookingDTO.GetDto> findByServiceId(Long serviceId);
    List<ServiceBookingDTO.GetDto> findByPatientId(Long patientId);
    List<ServiceBookingDTO.GetDto> findByStatus(BookingStatus status);
    List<ServiceBookingDTO.GetDto> findByPaymentMethod(PaymentMethod paymentMethod);
    List<ServiceBookingDTO.GetDto> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<ServiceBookingDTO.GetDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}