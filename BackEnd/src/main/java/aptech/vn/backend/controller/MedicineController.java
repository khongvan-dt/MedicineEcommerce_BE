package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ApiResponse;
import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin("*")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineDTO.GetDto>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO.GetDto> getMedicineById(@PathVariable Long id) {
        Optional<MedicineDTO.GetDto> medicine = medicineService.findById(id);
        return medicine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createMedicine(@RequestBody MedicineDTO.InsertDto medicineDTO) {
        MedicineDTO.InsertDto savedMedicine = medicineService.save(medicineDTO);
        return ResponseEntity.ok(new ApiResponse(true, "Thêm thuốc thành công!", savedMedicine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMedicine(@PathVariable Long id, @RequestBody MedicineDTO.InsertDto medicineDTO) {
        if (!medicineService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        MedicineDTO.InsertDto updatedMedicine = medicineService.save(medicineDTO);
        return ResponseEntity.ok(new ApiResponse(true, "Cập nhật thuốc thành công!", updatedMedicine));
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteMedicines(@RequestBody List<Long> ids) {
        medicineService.softDeleteByIds(ids);
        return ResponseEntity.ok(new ApiResponse(true, "Xóa thuốc thành công!", null));
    }

    @GetMapping("/by-code")
    public ResponseEntity<MedicineDTO.GetDto> getMedicineByCode(@RequestParam String code) {
        Optional<MedicineDTO.GetDto> medicine = medicineService.findByCode(code);
        return medicine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<MedicineDTO.GetDto>> getMedicineByName(@RequestParam String name) {
        return ResponseEntity.ok(medicineService.findByName(name));
    }
}
