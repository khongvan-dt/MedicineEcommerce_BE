package aptech.vn.backend.controller;

import aptech.vn.backend.entity.MedicineMedia;
import aptech.vn.backend.entity.MediaType;
import aptech.vn.backend.service.MedicineMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine-media")
public class MedicineMediaController {

    @Autowired
    private MedicineMediaService medicineMediaService;

    @GetMapping
    public ResponseEntity<List<MedicineMedia>> getAllMedicineMedia() {
        List<MedicineMedia> medicineMediaList = medicineMediaService.findAll();
        return new ResponseEntity<>(medicineMediaList, HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<MedicineMedia>> getAllMedicineMediaPaged(Pageable pageable) {
        Page<MedicineMedia> medicineMediaPage = medicineMediaService.findAll(pageable);
        return new ResponseEntity<>(medicineMediaPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineMedia> getMedicineMediaById(@PathVariable Long id) {
        return medicineMediaService.findById(id)
                .map(medicineMedia -> new ResponseEntity<>(medicineMedia, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MedicineMedia> createMedicineMedia(@RequestBody MedicineMedia medicineMedia) {
        MedicineMedia savedMedicineMedia = medicineMediaService.save(medicineMedia);
        return new ResponseEntity<>(savedMedicineMedia, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineMedia> updateMedicineMedia(@PathVariable Long id, @RequestBody MedicineMedia medicineMedia) {
        return medicineMediaService.findById(id)
                .map(existingMedicineMedia -> {
                    medicineMedia.setId(id);
                    MedicineMedia updatedMedicineMedia = medicineMediaService.save(medicineMedia);
                    return new ResponseEntity<>(updatedMedicineMedia, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicineMedia(@PathVariable Long id) {
        return medicineMediaService.findById(id)
                .map(medicineMedia -> {
                    medicineMediaService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<MedicineMedia>> getMedicineMediaByMedicineId(@PathVariable Long medicineId) {
        List<MedicineMedia> medicineMediaList = medicineMediaService.findByMedicineId(medicineId);
        return new ResponseEntity<>(medicineMediaList, HttpStatus.OK);
    }

    @GetMapping("/medicine/{medicineId}/type/{mediaType}")
    public ResponseEntity<List<MedicineMedia>> getMedicineMediaByMedicineIdAndMediaType(
            @PathVariable Long medicineId, @PathVariable MediaType mediaType) {
        List<MedicineMedia> medicineMediaList = medicineMediaService.findByMedicineIdAndMediaType(medicineId, mediaType);
        return new ResponseEntity<>(medicineMediaList, HttpStatus.OK);
    }

    @GetMapping("/medicine/{medicineId}/main-image")
    public ResponseEntity<MedicineMedia> getMainImageByMedicineId(@PathVariable Long medicineId) {
        return medicineMediaService.findMainImageByMedicineId(medicineId)
                .map(medicineMedia -> new ResponseEntity<>(medicineMedia, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/medicine/{medicineId}/main-image/{mediaId}")
    public ResponseEntity<Void> setMainImage(@PathVariable Long medicineId, @PathVariable Long mediaId) {
        medicineMediaService.setMainImage(medicineId, mediaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}