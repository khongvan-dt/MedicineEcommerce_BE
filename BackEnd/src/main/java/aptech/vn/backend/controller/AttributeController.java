package aptech.vn.backend.controller;

import aptech.vn.backend.dto.AttributeDTO;
import aptech.vn.backend.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attributes")
@CrossOrigin("*")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public ResponseEntity<List<AttributeDTO.GetAttributeDto>> getAllAttributes() {
        List<AttributeDTO.GetAttributeDto> attributes = attributeService.findAll();
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeDTO.GetAttributeDto> getAttributeById(@PathVariable Long id) {
        return attributeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<AttributeDTO.GetAttributeDto> saveOrUpdateAttribute(@RequestBody AttributeDTO.SaveAttributeDto attributeDTO) {
        AttributeDTO.GetAttributeDto savedAttribute = attributeService.saveOrUpdate(attributeDTO);
        return ResponseEntity.ok(savedAttribute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long id) {
        if (!attributeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        attributeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<AttributeDTO.GetAttributeDto>> findByName(@RequestParam String name) {
        List<AttributeDTO.GetAttributeDto> attributes = attributeService.findByName(name);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/price-in")
    public ResponseEntity<List<AttributeDTO.GetAttributeDto>> findByPriceInLessThanEqual(@RequestParam BigDecimal price) {
        List<AttributeDTO.GetAttributeDto> attributes = attributeService.findByPriceInLessThanEqual(price);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/price-out")
    public ResponseEntity<List<AttributeDTO.GetAttributeDto>> findByPriceOutLessThanEqual(@RequestParam BigDecimal price) {
        List<AttributeDTO.GetAttributeDto> attributes = attributeService.findByPriceOutLessThanEqual(price);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/stock")
    public ResponseEntity<List<AttributeDTO.GetAttributeDto>> findByStockGreaterThan(@RequestParam Integer stock) {
        List<AttributeDTO.GetAttributeDto> attributes = attributeService.findByStockGreaterThan(stock);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/expiry")
    public ResponseEntity<List<AttributeDTO.GetAttributeDto>> findByExpiryDateBefore(@RequestParam String date) {
        LocalDate expiryDate = LocalDate.parse(date);
        List<AttributeDTO.GetAttributeDto> attributes = attributeService.findByExpiryDateBefore(expiryDate);
        return ResponseEntity.ok(attributes);
    }
}