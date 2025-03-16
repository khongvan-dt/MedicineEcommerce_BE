package aptech.vn.backend.service;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServiceBookingService {
    List<ServiceBookingDTO.GetServiceBookingDto> findAll();
    Optional<ServiceBookingDTO.GetServiceBookingDto> findById(Long id);
    ServiceBookingDTO.GetServiceBookingDto saveOrUpdate(ServiceBookingDTO.SaveServiceBookingDto serviceBookingDTO);
    void deleteById(Long id);
    List<ServiceBookingDTO.GetServiceBookingDto> findByServiceId(Long serviceId);
    List<ServiceBookingDTO.GetServiceBookingDto> findByPatientId(Long patientId);
    List<ServiceBookingDTO.GetServiceBookingDto> findByStatus(BookingStatus status);
    List<ServiceBookingDTO.GetServiceBookingDto> findByPaymentMethod(PaymentMethod paymentMethod);
    List<ServiceBookingDTO.GetServiceBookingDto> findByTotalPriceGreaterThanEqual(BigDecimal amount);
    List<ServiceBookingDTO.GetServiceBookingDto> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}