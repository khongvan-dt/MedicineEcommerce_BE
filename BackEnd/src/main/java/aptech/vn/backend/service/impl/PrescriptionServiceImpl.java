package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.PrescriptionDTO;
import aptech.vn.backend.entity.Prescription;
import aptech.vn.backend.mapper.PrescriptionMapper;
import aptech.vn.backend.repository.PrescriptionRepository;
import aptech.vn.backend.service.PrescriptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, PrescriptionMapper prescriptionMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    public List<PrescriptionDTO> findAll() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrescriptionDTO> findById(Long id) {
        return prescriptionRepository.findById(id).map(prescriptionMapper::toDto);
    }

    @Override
    public PrescriptionDTO save(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = prescriptionMapper.toEntity(prescriptionDTO);
        prescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDto(prescription);
    }

    @Override
    public void deleteById(Long id) {
        prescriptionRepository.deleteById(id);
    }

    @Override
    public List<PrescriptionDTO> findByDoctorId(Long doctorId) {
        return prescriptionRepository.findByDoctor_Id(doctorId)
                .stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO> findByPatientId(Long patientId) {
        return prescriptionRepository.findByPatient_Id(patientId)
                .stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO> findByMedicineId(Long medicineId) {
        return prescriptionRepository.findByMedicine_Id(medicineId)
                .stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO> findByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return prescriptionRepository.findByPatient_IdAndDoctor_Id(patientId, doctorId)
                .stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO> findByPatientIdAndMedicineId(Long patientId, Long medicineId) {
        return prescriptionRepository.findByPatient_IdAndMedicine_Id(patientId, medicineId)
                .stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }
}
