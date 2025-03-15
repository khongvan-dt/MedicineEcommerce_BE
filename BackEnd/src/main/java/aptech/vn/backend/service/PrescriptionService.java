package aptech.vn.backend.service;

import aptech.vn.backend.dto.PrescriptionDTO;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {
    List<PrescriptionDTO.GetDto> findAll();
    Optional<PrescriptionDTO.GetDto> findById(Long id);
    PrescriptionDTO.GetDto saveOrUpdate(PrescriptionDTO.SaveDto prescriptionDTO);
    void deleteById(Long id);
    List<PrescriptionDTO.GetDto> findByDoctorId(Long doctorId);
    List<PrescriptionDTO.GetDto> findByPatientId(Long patientId);
    List<PrescriptionDTO.GetDto> findByMedicineId(Long medicineId);
    List<PrescriptionDTO.GetDto> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<PrescriptionDTO.GetDto> findByPatientIdAndMedicineId(Long patientId, Long medicineId);
}