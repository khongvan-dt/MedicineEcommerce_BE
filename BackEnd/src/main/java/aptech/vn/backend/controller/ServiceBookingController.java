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
import java.util.Optional;

@RestController
@RequestMapping("/api/service-bookings")
public class ServiceBookingController {

    private final ServiceBookingService serviceBookingService;

    public ServiceBookingController(ServiceBookingService serviceBookingService) {
        this.serviceBookingService = serviceBookingService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceBookingDTO>> getAllServiceBookings() {
        List<ServiceBookingDTO> bookings = serviceBookingService.findAll();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceBookingDTO> getServiceBookingById(@PathVariable Long id) {
        Optional<ServiceBookingDTO> booking = serviceBookingService.findById(id);
        return booking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceBookingDTO> createServiceBooking(@RequestBody ServiceBookingDTO serviceBookingDTO) {
        ServiceBookingDTO savedBooking = serviceBookingService.save(serviceBookingDTO);
        return ResponseEntity.ok(savedBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceBookingDTO> updateServiceBooking(@PathVariable Long id, @RequestBody ServiceBookingDTO serviceBookingDTO) {
        if (!serviceBookingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ServiceBookingDTO updatedBooking = serviceBookingService.save(serviceBookingDTO);
        return ResponseEntity.ok(updatedBooking);
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
    public ResponseEntity<List<ServiceBookingDTO>> getServiceBookingsByServiceId(@PathVariable Long serviceId) {
        List<ServiceBookingDTO> bookings = serviceBookingService.findByServiceId(serviceId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<ServiceBookingDTO>> getServiceBookingsByPatientId(@PathVariable Long patientId) {
        List<ServiceBookingDTO> bookings = serviceBookingService.findByPatientId(patientId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<ServiceBookingDTO>> getServiceBookingsByStatus(@PathVariable BookingStatus status) {
        List<ServiceBookingDTO> bookings = serviceBookingService.findByStatus(status);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-payment-method/{paymentMethod}")
    public ResponseEntity<List<ServiceBookingDTO>> getServiceBookingsByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
        List<ServiceBookingDTO> bookings = serviceBookingService.findByPaymentMethod(paymentMethod);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-min-price/{amount}")
    public ResponseEntity<List<ServiceBookingDTO>> getServiceBookingsByMinPrice(@PathVariable BigDecimal amount) {
        List<ServiceBookingDTO> bookings = serviceBookingService.findByTotalPriceGreaterThanEqual(amount);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<ServiceBookingDTO>> getServiceBookingsByDateRange(
            @RequestParam("start") LocalDateTime start,
            @RequestParam("end") LocalDateTime end) {
        List<ServiceBookingDTO> bookings = serviceBookingService.findByCreatedBetween(start, end);
        return ResponseEntity.ok(bookings);
    }
}
