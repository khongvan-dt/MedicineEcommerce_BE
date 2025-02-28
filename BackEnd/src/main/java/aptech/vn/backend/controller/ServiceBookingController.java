package aptech.vn.backend.controller;

import aptech.vn.backend.entity.BookingStatus;
import aptech.vn.backend.entity.ServiceBooking;
import aptech.vn.backend.service.ServiceBookingService;
import aptech.vn.backend.service.impl.ServiceBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/service-bookings")
public class ServiceBookingController {

    private final ServiceBookingService serviceBookingService;

    @Autowired
    public ServiceBookingController(ServiceBookingServiceImpl serviceBookingService) {
        this.serviceBookingService = serviceBookingService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceBooking>> getAllServiceBookings() {
        return ResponseEntity.ok(serviceBookingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceBooking> getServiceBookingById(@PathVariable Long id) {
        return serviceBookingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ServiceBooking>> getServiceBookingsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(serviceBookingService.findByPatientId(patientId));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ServiceBooking>> getServiceBookingsByServiceId(@PathVariable Long serviceId) {
        return ResponseEntity.ok(serviceBookingService.findByServiceId(serviceId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ServiceBooking>> getServiceBookingsByStatus(@PathVariable BookingStatus status) {
        return ResponseEntity.ok(serviceBookingService.findByStatus(status));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ServiceBooking>> getServiceBookingsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(serviceBookingService.findByCreatedAtBetween(start, end));
    }

    @PostMapping
    public ResponseEntity<ServiceBooking> createServiceBooking(@RequestBody ServiceBooking serviceBooking) {
        return new ResponseEntity<>(serviceBookingService.save(serviceBooking), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceBooking> updateServiceBooking(@PathVariable Long id, @RequestBody ServiceBooking serviceBooking) {
        return serviceBookingService.findById(id)
                .map(existingBooking -> {
                    serviceBooking.setId(id);
                    return ResponseEntity.ok(serviceBookingService.save(serviceBooking));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateServiceBookingStatus(
            @PathVariable Long id,
            @RequestBody Map<String, BookingStatus> statusMap) {

        BookingStatus newStatus = statusMap.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().body("Status field is required");
        }

        return serviceBookingService.findById(id)
                .map(existingBooking -> {
                    boolean updated = serviceBookingService.updateStatus(id, newStatus);
                    if (updated) {
                        return ResponseEntity.ok("Service booking status updated successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to update service booking status");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceBooking(@PathVariable Long id) {
        return serviceBookingService.findById(id)
                .map(serviceBooking -> {
                    serviceBookingService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}