package aptech.vn.backend.controller;

import aptech.vn.backend.entity.ServiceType;
import aptech.vn.backend.service.ServiceTypeService;
import aptech.vn.backend.service.impl.ServiceTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-types")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeController(ServiceTypeServiceImpl serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceType>> getAllServiceTypes() {
        return ResponseEntity.ok(serviceTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceType> getServiceTypeById(@PathVariable Long id) {
        return serviceTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ServiceType> getServiceTypeByName(@PathVariable String name) {
        return serviceTypeService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceType> createServiceType(@RequestBody ServiceType serviceType) {
        return new ResponseEntity<>(serviceTypeService.save(serviceType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceType> updateServiceType(@PathVariable Long id, @RequestBody ServiceType serviceType) {
        return serviceTypeService.findById(id)
                .map(existingServiceType -> {
                    serviceType.setId(id);
                    return ResponseEntity.ok(serviceTypeService.save(serviceType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Long id) {
        return serviceTypeService.findById(id)
                .map(serviceType -> {
                    serviceTypeService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}