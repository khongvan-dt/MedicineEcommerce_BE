package aptech.vn.backend.service;

import aptech.vn.backend.dto.PrescriptionDTO;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {
    List<PrescriptionDTO.GetPrescriptionDto> findAll();
    Optional<PrescriptionDTO.GetPrescriptionDto> findById(Long id);
    PrescriptionDTO.GetPrescriptionDto saveOrUpdate(PrescriptionDTO.SavePrescriptionDto prescriptionDTO);
    void deleteById(Long id);
    List<PrescriptionDTO.GetPrescriptionDto> findByDoctorId(Long doctorId);
    List<PrescriptionDTO.GetPrescriptionDto> findByPatientId(Long patientId);
    List<PrescriptionDTO.GetPrescriptionDto> findByMedicineId(Long medicineId);
    List<PrescriptionDTO.GetPrescriptionDto> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<PrescriptionDTO.GetPrescriptionDto> findByPatientIdAndMedicineId(Long patientId, Long medicineId);
}