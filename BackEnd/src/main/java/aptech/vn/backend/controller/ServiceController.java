package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ServiceDTO;
import aptech.vn.backend.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = serviceService.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        Optional<ServiceDTO> service = serviceService.findById(id);
        return service.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO savedService = serviceService.save(serviceDTO);
        return ResponseEntity.ok(savedService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        if (!serviceService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ServiceDTO updatedService = serviceService.save(serviceDTO);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (!serviceService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        serviceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<ServiceDTO>> getServicesByName(@PathVariable String name) {
        List<ServiceDTO> services = serviceService.findByName(name);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/by-name-pattern")
    public ResponseEntity<List<ServiceDTO>> getServicesByNamePattern(@RequestParam String namePattern) {
        List<ServiceDTO> services = serviceService.findByNameContaining(namePattern);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/by-max-price/{maxPrice}")
    public ResponseEntity<List<ServiceDTO>> getServicesByMaxPrice(@PathVariable BigDecimal maxPrice) {
        List<ServiceDTO> services = serviceService.findByPriceLessThanEqual(maxPrice);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/by-min-price/{minPrice}")
    public ResponseEntity<List<ServiceDTO>> getServicesByMinPrice(@PathVariable BigDecimal minPrice) {
        List<ServiceDTO> services = serviceService.findByPriceGreaterThanEqual(minPrice);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/by-price-range")
    public ResponseEntity<List<ServiceDTO>> getServicesByPriceRange(
            @RequestParam("min") BigDecimal minPrice,
            @RequestParam("max") BigDecimal maxPrice) {
        List<ServiceDTO> services = serviceService.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(services);
    }
}
