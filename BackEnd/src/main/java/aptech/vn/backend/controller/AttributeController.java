package aptech.vn.backend.controller;

import aptech.vn.backend.dto.AttributeDTO;
import aptech.vn.backend.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public ResponseEntity<List<AttributeDTO>> getAllAttributes() {
        List<AttributeDTO> attributes = attributeService.findAll();
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeDTO> getAttributeById(@PathVariable Long id) {
        Optional<AttributeDTO> attribute = attributeService.findById(id);
        return attribute.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AttributeDTO> createAttribute(@RequestBody AttributeDTO attributeDTO) {
        AttributeDTO savedAttribute = attributeService.save(attributeDTO);
        return ResponseEntity.ok(savedAttribute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeDTO> updateAttribute(@PathVariable Long id, @RequestBody AttributeDTO attributeDTO) {
        if (!attributeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AttributeDTO updatedAttribute = attributeService.save(attributeDTO);
        return ResponseEntity.ok(updatedAttribute);
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
    public ResponseEntity<List<AttributeDTO>> findByName(@RequestParam String name) {
        List<AttributeDTO> attributes = attributeService.findByName(name);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/price-in")
    public ResponseEntity<List<AttributeDTO>> findByPriceInLessThanEqual(@RequestParam BigDecimal price) {
        List<AttributeDTO> attributes = attributeService.findByPriceInLessThanEqual(price);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/price-out")
    public ResponseEntity<List<AttributeDTO>> findByPriceOutLessThanEqual(@RequestParam BigDecimal price) {
        List<AttributeDTO> attributes = attributeService.findByPriceOutLessThanEqual(price);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/stock")
    public ResponseEntity<List<AttributeDTO>> findByStockGreaterThan(@RequestParam Integer stock) {
        List<AttributeDTO> attributes = attributeService.findByStockGreaterThan(stock);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/expiry")
    public ResponseEntity<List<AttributeDTO>> findByExpiryDateBefore(@RequestParam String date) {
        LocalDate expiryDate = LocalDate.parse(date);
        List<AttributeDTO> attributes = attributeService.findByExpiryDateBefore(expiryDate);
        return ResponseEntity.ok(attributes);
    }
}
