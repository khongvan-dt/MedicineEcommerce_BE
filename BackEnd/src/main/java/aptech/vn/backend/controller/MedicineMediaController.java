package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MediaType;
import aptech.vn.backend.service.MedicineMediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicine-media")
public class MedicineMediaController {

    private final MedicineMediaService medicineMediaService;

    public MedicineMediaController(MedicineMediaService medicineMediaService) {
        this.medicineMediaService = medicineMediaService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineMediaDTO>> getAllMedicineMedia() {
        List<MedicineMediaDTO> mediaList = medicineMediaService.findAll();
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineMediaDTO> getMedicineMediaById(@PathVariable Long id) {
        Optional<MedicineMediaDTO> media = medicineMediaService.findById(id);
        return media.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicineMediaDTO> createMedicineMedia(@RequestBody MedicineMediaDTO medicineMediaDTO) {
        MedicineMediaDTO savedMedia = medicineMediaService.save(medicineMediaDTO);
        return ResponseEntity.ok(savedMedia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineMediaDTO> updateMedicineMedia(@PathVariable Long id, @RequestBody MedicineMediaDTO medicineMediaDTO) {
        if (!medicineMediaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        MedicineMediaDTO updatedMedia = medicineMediaService.save(medicineMediaDTO);
        return ResponseEntity.ok(updatedMedia);
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
    public ResponseEntity<List<MedicineMediaDTO>> getMediaByMedicineId(@PathVariable Long medicineId) {
        List<MedicineMediaDTO> mediaList = medicineMediaService.findByMedicineId(medicineId);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-media-type/{mediaType}")
    public ResponseEntity<List<MedicineMediaDTO>> getMediaByType(@PathVariable MediaType mediaType) {
        List<MedicineMediaDTO> mediaList = medicineMediaService.findByMediaType(mediaType);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-medicine-and-media-type")
    public ResponseEntity<List<MedicineMediaDTO>> getMediaByMedicineAndType(@RequestParam Long medicineId, @RequestParam MediaType mediaType) {
        List<MedicineMediaDTO> mediaList = medicineMediaService.findByMedicineIdAndMediaType(medicineId, mediaType);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/main-image/{medicineId}")
    public ResponseEntity<MedicineMediaDTO> getMainImageByMedicineId(@PathVariable Long medicineId) {
        Optional<MedicineMediaDTO> mainImage = medicineMediaService.findMainImageByMedicineId(medicineId);
        return mainImage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
