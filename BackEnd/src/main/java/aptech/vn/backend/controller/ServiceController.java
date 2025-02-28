package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Service;
import aptech.vn.backend.service.ServiceService;
import aptech.vn.backend.service.impl.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceServiceImpl serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(serviceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        return serviceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Service>> searchServicesByName(@RequestParam String keyword) {
        return ResponseEntity.ok(serviceService.findByNameContaining(keyword));
    }

    @GetMapping("/price/less-than")
    public ResponseEntity<List<Service>> getServicesByPriceLessThan(@RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(serviceService.findByPriceLessThan(maxPrice));
    }

    @GetMapping("/price/greater-than")
    public ResponseEntity<List<Service>> getServicesByPriceGreaterThan(@RequestParam BigDecimal minPrice) {
        return ResponseEntity.ok(serviceService.findByPriceGreaterThan(minPrice));
    }

    @GetMapping("/price/between")
    public ResponseEntity<List<Service>> getServicesByPriceBetween(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(serviceService.findByPriceBetween(minPrice, maxPrice));
    }

    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        return new ResponseEntity<>(serviceService.save(service), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service service) {
        return serviceService.findById(id)
                .map(existingService -> {
                    service.setId(id);
                    return ResponseEntity.ok(serviceService.save(service));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        return serviceService.findById(id)
                .map(service -> {
                    serviceService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}