package aptech.vn.backend.service;

import aptech.vn.backend.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {
    Prescription save(Prescription prescription);
    Optional<Prescription> findById(Long id);
    List<Prescription> findAll();
    Page<Prescription> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Prescription> findByPatientId(Long patientId);
    List<Prescription> findByDoctorId(Long doctorId);
    List<Prescription> findByMedicineId(Long medicineId);
}