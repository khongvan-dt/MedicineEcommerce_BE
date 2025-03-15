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
    public ResponseEntity<List<AttributeDTO.GetDto>> getAllAttributes() {
        List<AttributeDTO.GetDto> attributes = attributeService.findAll();
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeDTO.GetDto> getAttributeById(@PathVariable Long id) {
        return attributeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<AttributeDTO.GetDto> saveOrUpdateAttribute(@RequestBody AttributeDTO.SaveDto attributeDTO) {
        AttributeDTO.GetDto savedAttribute = attributeService.saveOrUpdate(attributeDTO);
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
    public ResponseEntity<List<AttributeDTO.GetDto>> findByName(@RequestParam String name) {
        List<AttributeDTO.GetDto> attributes = attributeService.findByName(name);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/price-in")
    public ResponseEntity<List<AttributeDTO.GetDto>> findByPriceInLessThanEqual(@RequestParam BigDecimal price) {
        List<AttributeDTO.GetDto> attributes = attributeService.findByPriceInLessThanEqual(price);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/price-out")
    public ResponseEntity<List<AttributeDTO.GetDto>> findByPriceOutLessThanEqual(@RequestParam BigDecimal price) {
        List<AttributeDTO.GetDto> attributes = attributeService.findByPriceOutLessThanEqual(price);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/stock")
    public ResponseEntity<List<AttributeDTO.GetDto>> findByStockGreaterThan(@RequestParam Integer stock) {
        List<AttributeDTO.GetDto> attributes = attributeService.findByStockGreaterThan(stock);
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/search/expiry")
    public ResponseEntity<List<AttributeDTO.GetDto>> findByExpiryDateBefore(@RequestParam String date) {
        LocalDate expiryDate = LocalDate.parse(date);
        List<AttributeDTO.GetDto> attributes = attributeService.findByExpiryDateBefore(expiryDate);
        return ResponseEntity.ok(attributes);
    }
}