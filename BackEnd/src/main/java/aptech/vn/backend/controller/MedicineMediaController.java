package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MediaType;
import aptech.vn.backend.service.MedicineMediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine-media")
@CrossOrigin("*")
public class MedicineMediaController {

    private final MedicineMediaService medicineMediaService;

    public MedicineMediaController(MedicineMediaService medicineMediaService) {
        this.medicineMediaService = medicineMediaService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineMediaDTO.GetMedicineMediaDto>> getAllMedicineMedia() {
        List<MedicineMediaDTO.GetMedicineMediaDto> mediaList = medicineMediaService.findAll();
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineMediaDTO.GetMedicineMediaDto> getMedicineMediaById(@PathVariable Long id) {
        return medicineMediaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<MedicineMediaDTO.GetMedicineMediaDto> saveOrUpdateMedicineMedia(@RequestBody MedicineMediaDTO.SaveMedicineMediaDto medicineMediaDTO) {
        MedicineMediaDTO.GetMedicineMediaDto savedMedia = medicineMediaService.saveOrUpdate(medicineMediaDTO);
        return ResponseEntity.ok(savedMedia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicineMedia(@PathVariable Long id) {
        if (!medicineMediaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicineMediaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-medicine/{medicineId}")
    public ResponseEntity<List<MedicineMediaDTO.GetMedicineMediaDto>> getMediaByMedicineId(@PathVariable Long medicineId) {
        List<MedicineMediaDTO.GetMedicineMediaDto> mediaList = medicineMediaService.findByMedicineId(medicineId);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-media-type/{mediaType}")
    public ResponseEntity<List<MedicineMediaDTO.GetMedicineMediaDto>> getMediaByType(@PathVariable MediaType mediaType) {
        List<MedicineMediaDTO.GetMedicineMediaDto> mediaList = medicineMediaService.findByMediaType(mediaType);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-medicine-and-media-type")
    public ResponseEntity<List<MedicineMediaDTO.GetMedicineMediaDto>> getMediaByMedicineAndType(
            @RequestParam Long medicineId,
            @RequestParam MediaType mediaType) {
        List<MedicineMediaDTO.GetMedicineMediaDto> mediaList = medicineMediaService.findByMedicineIdAndMediaType(medicineId, mediaType);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/main-image/{medicineId}")
    public ResponseEntity<MedicineMediaDTO.GetMedicineMediaDto> getMainImageByMedicineId(@PathVariable Long medicineId) {
        return medicineMediaService.findMainImageByMedicineId(medicineId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}