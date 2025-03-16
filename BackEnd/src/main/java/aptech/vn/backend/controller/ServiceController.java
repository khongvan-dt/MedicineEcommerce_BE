package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ServiceDTO;
import aptech.vn.backend.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO.GetServiceDto>> getAllServices() {
        List<ServiceDTO.GetServiceDto> services = serviceService.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO.GetServiceDto> getServiceById(@PathVariable Long id) {
        return serviceService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceDTO.GetServiceDto> saveOrUpdateService(@RequestBody ServiceDTO.SaveServiceDto serviceDTO) {
        ServiceDTO.GetServiceDto savedService = serviceService.saveOrUpdate(serviceDTO);
        return ResponseEntity.ok(savedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (!serviceService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        serviceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<ServiceDTO.GetServiceDto>> getServicesByName(@RequestParam String name) {
        List<ServiceDTO.GetServiceDto> services = serviceService.findByName(name);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceDTO.GetServiceDto>> getServicesByNameContaining(@RequestParam String query) {
        List<ServiceDTO.GetServiceDto> services = serviceService.findByNameContaining(query);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/by-max-price")
    public ResponseEntity<List<ServiceDTO.GetServiceDto>> getServicesByMaxPrice(@RequestParam BigDecimal maxPrice) {
        List<ServiceDTO.GetServiceDto> services = serviceService.findByPriceLessThanEqual(maxPrice);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/by-min-price")
    public ResponseEntity<List<ServiceDTO.GetServiceDto>> getServicesByMinPrice(@RequestParam BigDecimal minPrice) {
        List<ServiceDTO.GetServiceDto> services = serviceService.findByPriceGreaterThanEqual(minPrice);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/by-price-range")
    public ResponseEntity<List<ServiceDTO.GetServiceDto>> getServicesByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<ServiceDTO.GetServiceDto> services = serviceService.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(services);
    }
}