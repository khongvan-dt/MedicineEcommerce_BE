package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ServiceTypeDTO;
import aptech.vn.backend.service.ServiceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-types")
@CrossOrigin("*")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceTypeDTO.GetServiceTypeDto>> getAllServiceTypes() {
        return ResponseEntity.ok(serviceTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDTO.GetServiceTypeDto> getServiceTypeById(@PathVariable Long id) {
        return serviceTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ServiceTypeDTO.GetServiceTypeDto> getServiceTypeByName(@PathVariable String name) {
        return serviceTypeService.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceTypeDTO.GetServiceTypeDto>> getServiceTypesByNameContaining(@RequestParam String query) {
        return ResponseEntity.ok(serviceTypeService.findByNameContaining(query));
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceTypeDTO.GetServiceTypeDto> saveOrUpdateServiceType(@RequestBody ServiceTypeDTO.SaveServiceTypeDto serviceTypeDTO) {
        ServiceTypeDTO.GetServiceTypeDto savedType = serviceTypeService.saveOrUpdate(serviceTypeDTO);
        return new ResponseEntity<>(savedType, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Long id) {
        if (!serviceTypeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        serviceTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}