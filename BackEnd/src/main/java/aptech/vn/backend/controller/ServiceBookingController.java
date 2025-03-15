package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.PaymentMethod;
import aptech.vn.backend.service.ServiceBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/service-bookings")
@CrossOrigin("*")
public class ServiceBookingController {

    private final ServiceBookingService serviceBookingService;

    public ServiceBookingController(ServiceBookingService serviceBookingService) {
        this.serviceBookingService = serviceBookingService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceBookingDTO.GetDto>> getAllServiceBookings() {
        List<ServiceBookingDTO.GetDto> bookings = serviceBookingService.findAll();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceBookingDTO.GetDto> getServiceBookingById(@PathVariable Long id) {
        return serviceBookingService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceBookingDTO.GetDto> saveOrUpdateServiceBooking(@RequestBody ServiceBookingDTO.SaveDto serviceBookingDTO) {
        ServiceBookingDTO.GetDto savedBooking = serviceBookingService.saveOrUpdate(serviceBookingDTO);
        return ResponseEntity.ok(savedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceBooking(@PathVariable Long id) {
        if (!serviceBookingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        serviceBookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-service/{serviceId}")
    public ResponseEntity<List<ServiceBookingDTO.GetDto>> getServiceBookingsByServiceId(@PathVariable Long serviceId) {
        List<ServiceBookingDTO.GetDto> bookings = serviceBookingService.findByServiceId(serviceId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<ServiceBookingDTO.GetDto>> getServiceBookingsByPatientId(@PathVariable Long patientId) {
        List<ServiceBookingDTO.GetDto> bookings = serviceBookingService.findByPatientId(patientId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<ServiceBookingDTO.GetDto>> getServiceBookingsByStatus(@PathVariable BookingStatus status) {
        List<ServiceBookingDTO.GetDto> bookings = serviceBookingService.findByStatus(status);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-payment-method/{paymentMethod}")
    public ResponseEntity<List<ServiceBookingDTO.GetDto>> getServiceBookingsByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
        List<ServiceBookingDTO.GetDto> bookings = serviceBookingService.findByPaymentMethod(paymentMethod);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-min-price/{amount}")
    public ResponseEntity<List<ServiceBookingDTO.GetDto>> getServiceBookingsByMinPrice(@PathVariable BigDecimal amount) {
        List<ServiceBookingDTO.GetDto> bookings = serviceBookingService.findByTotalPriceGreaterThanEqual(amount);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<ServiceBookingDTO.GetDto>> getServiceBookingsByDateRange(
            @RequestParam("start") LocalDateTime start,
            @RequestParam("end") LocalDateTime end) {
        List<ServiceBookingDTO.GetDto> bookings = serviceBookingService.findByCreatedBetween(start, end);
        return ResponseEntity.ok(bookings);
    }
}