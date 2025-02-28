package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Attribute;
import aptech.vn.backend.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @GetMapping
    public ResponseEntity<List<Attribute>> getAllAttributes() {
        List<Attribute> attributes = attributeService.findAll();
        return new ResponseEntity<>(attributes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable Long id) {
        return attributeService.findById(id)
                .map(attribute -> new ResponseEntity<>(attribute, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute attribute) {
        Attribute savedAttribute = attributeService.save(attribute);
        return new ResponseEntity<>(savedAttribute, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attribute> updateAttribute(@PathVariable Long id, @RequestBody Attribute attribute) {
        return attributeService.findById(id)
                .map(existingAttribute -> {
                    attribute.setId(id);
                    Attribute updatedAttribute = attributeService.save(attribute);
                    return new ResponseEntity<>(updatedAttribute, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long id) {
        return attributeService.findById(id)
                .map(attribute -> {
                    attributeService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Attribute>> getAttributesByName(@PathVariable String name) {
        List<Attribute> attributes = attributeService.findByName(name);
        return new ResponseEntity<>(attributes, HttpStatus.OK);
    }

    @GetMapping("/expiry-before")
    public ResponseEntity<List<Attribute>> getAttributesExpiringBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Attribute> attributes = attributeService.findByExpiryDateBefore(date);
        return new ResponseEntity<>(attributes, HttpStatus.OK);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Attribute>> getAttributesWithLowStock(@RequestParam Integer threshold) {
        List<Attribute> attributes = attributeService.findByStockLessThan(threshold);
        return new ResponseEntity<>(attributes, HttpStatus.OK);
    }
}