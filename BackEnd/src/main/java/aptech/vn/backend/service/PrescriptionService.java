package aptech.vn.backend.service;

import aptech.vn.backend.dto.PrescriptionDTO;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {
    List<PrescriptionDTO> findAll();
    Optional<PrescriptionDTO> findById(Long id);
    PrescriptionDTO save(PrescriptionDTO prescriptionDTO);
    void deleteById(Long id);
    List<PrescriptionDTO> findByDoctorId(Long doctorId);
    List<PrescriptionDTO> findByPatientId(Long patientId);
    List<PrescriptionDTO> findByMedicineId(Long medicineId);
    List<PrescriptionDTO> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<PrescriptionDTO> findByPatientIdAndMedicineId(Long patientId, Long medicineId);
}