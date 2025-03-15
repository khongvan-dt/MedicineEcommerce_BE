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
    public ResponseEntity<List<MedicineMediaDTO.GetDto>> getAllMedicineMedia() {
        List<MedicineMediaDTO.GetDto> mediaList = medicineMediaService.findAll();
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineMediaDTO.GetDto> getMedicineMediaById(@PathVariable Long id) {
        return medicineMediaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<MedicineMediaDTO.GetDto> saveOrUpdateMedicineMedia(@RequestBody MedicineMediaDTO.SaveDto medicineMediaDTO) {
        MedicineMediaDTO.GetDto savedMedia = medicineMediaService.saveOrUpdate(medicineMediaDTO);
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
    public ResponseEntity<List<MedicineMediaDTO.GetDto>> getMediaByMedicineId(@PathVariable Long medicineId) {
        List<MedicineMediaDTO.GetDto> mediaList = medicineMediaService.findByMedicineId(medicineId);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-media-type/{mediaType}")
    public ResponseEntity<List<MedicineMediaDTO.GetDto>> getMediaByType(@PathVariable MediaType mediaType) {
        List<MedicineMediaDTO.GetDto> mediaList = medicineMediaService.findByMediaType(mediaType);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-medicine-and-media-type")
    public ResponseEntity<List<MedicineMediaDTO.GetDto>> getMediaByMedicineAndType(
            @RequestParam Long medicineId,
            @RequestParam MediaType mediaType) {
        List<MedicineMediaDTO.GetDto> mediaList = medicineMediaService.findByMedicineIdAndMediaType(medicineId, mediaType);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/main-image/{medicineId}")
    public ResponseEntity<MedicineMediaDTO.GetDto> getMainImageByMedicineId(@PathVariable Long medicineId) {
        return medicineMediaService.findMainImageByMedicineId(medicineId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}