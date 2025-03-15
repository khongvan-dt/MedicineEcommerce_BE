package aptech.vn.backend.controller;

import aptech.vn.backend.dto.ApiResponse;
import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return medicineService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveOrUpdateMedicine(@RequestBody MedicineDTO.SaveDto medicineDTO) {
        MedicineDTO.GetDto savedMedicine = medicineService.saveOrUpdate(medicineDTO);
        return ResponseEntity.ok(new ApiResponse(true,
                medicineDTO.getId() == null || medicineDTO.getId() == 0 ?
                        "Thêm thuốc thành công!" : "Cập nhật thuốc thành công!",
                savedMedicine));
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteMedicines(@RequestBody List<Long> ids) {
        medicineService.softDeleteByIds(ids);
        return ResponseEntity.ok(new ApiResponse(true, "Xóa thuốc thành công!", null));
    }

    @GetMapping("/by-code")
    public ResponseEntity<MedicineDTO.GetDto> getMedicineByCode(@RequestParam String code) {
        return medicineService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<MedicineDTO.GetDto>> getMedicineByName(@RequestParam String name) {
        return ResponseEntity.ok(medicineService.findByNameContaining(name));
    }
}