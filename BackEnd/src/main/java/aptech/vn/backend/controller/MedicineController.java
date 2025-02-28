package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.findAll();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        return medicineService.findById(id)
                .map(medicine -> new ResponseEntity<>(medicine, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        Medicine savedMedicine = medicineService.save(medicine);
        return new ResponseEntity<>(savedMedicine, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicine) {
        return medicineService.findById(id)
                .map(existingMedicine -> {
                    medicine.setId(id);
                    Medicine updatedMedicine = medicineService.save(medicine);
                    return new ResponseEntity<>(updatedMedicine, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        return medicineService.findById(id)
                .map(medicine -> {
                    medicineService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Medicine> getMedicineByCode(@PathVariable String code) {
        return medicineService.findByCode(code)
                .map(medicine -> new ResponseEntity<>(medicine, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Medicine>> getMedicinesByName(@PathVariable String name) {
        List<Medicine> medicines = medicineService.findByName(name);
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<Medicine>> getMedicinesByBrandId(@PathVariable Long brandId) {
        List<Medicine> medicines = medicineService.findByBrandId(brandId);
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/origin/{origin}")
    public ResponseEntity<List<Medicine>> getMedicinesByOrigin(@PathVariable String origin) {
        List<Medicine> medicines = medicineService.findByOrigin(origin);
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<List<Medicine>> getMedicinesByManufacturer(@PathVariable String manufacturer) {
        List<Medicine> medicines = medicineService.findByManufacturer(manufacturer);
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Medicine>> getMedicinesContainingName(@RequestParam String name) {
        List<Medicine> medicines = medicineService.findByNameContaining(name);
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }
}