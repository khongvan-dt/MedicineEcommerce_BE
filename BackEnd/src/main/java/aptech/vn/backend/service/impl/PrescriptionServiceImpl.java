package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.PrescriptionDTO;
import aptech.vn.backend.entity.DoctorService;
import aptech.vn.backend.entity.DoctorProfile;
import aptech.vn.backend.entity.Medicine;
import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.entity.Prescription;
import aptech.vn.backend.mapper.PrescriptionMapper;
import aptech.vn.backend.repository.DoctorProfileRepository;
import aptech.vn.backend.repository.DoctorServiceRepository;
import aptech.vn.backend.repository.MedicineRepository;
import aptech.vn.backend.repository.PatientProfileRepository;
 import aptech.vn.backend.repository.PrescriptionRepository;
import aptech.vn.backend.service.PrescriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorProfileRepository doctorRepository;
    private final PatientProfileRepository patientRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionMapper prescriptionMapper;

    public PrescriptionServiceImpl(
            PrescriptionRepository prescriptionRepository,
            DoctorProfileRepository doctorRepository,
            PatientProfileRepository patientRepository,
            MedicineRepository medicineRepository,
            PrescriptionMapper prescriptionMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    public List<PrescriptionDTO.GetDto> findAll() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescriptionMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrescriptionDTO.GetDto> findById(Long id) {
        return prescriptionRepository.findById(id)
                .map(prescriptionMapper::toGetDto);
    }

    @Override
    @Transactional
    public PrescriptionDTO.GetDto saveOrUpdate(PrescriptionDTO.SaveDto prescriptionDTO) {
        Prescription prescription;

        if (prescriptionDTO.getId() == null || prescriptionDTO.getId() == 0) {
            // INSERT case
            prescription = new Prescription();
            prescription.setCreatedAt(LocalDateTime.now());
            prescription.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Prescription> existingPrescription = prescriptionRepository.findById(prescriptionDTO.getId());
            if (existingPrescription.isEmpty()) {
                throw new RuntimeException("Prescription not found with ID: " + prescriptionDTO.getId());
            }
            prescription = existingPrescription.get();
            prescription.setUpdatedAt(LocalDateTime.now());
        }

        // SỬA: Thay đổi kiểu biến doctor từ Repository thành Entity
        DoctorProfile doctor = doctorRepository.findById(prescriptionDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + prescriptionDTO.getDoctorId()));
        prescription.setDoctor(doctor);

        // SỬA: Thay đổi kiểu biến patient từ Repository thành Entity
        PatientProfile patient = patientRepository.findById(prescriptionDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + prescriptionDTO.getPatientId()));
        prescription.setPatient(patient);

        // Xử lý medicine relationship
        Medicine medicine = medicineRepository.findById(prescriptionDTO.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + prescriptionDTO.getMedicineId()));
        prescription.setMedicine(medicine);

        // Cập nhật trường còn lại
        prescription.setDosage(prescriptionDTO.getDosage());

        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toGetDto(savedPrescription);
    }
    @Override
    public void deleteById(Long id) {
        prescriptionRepository.deleteById(id);
    }

    @Override
    public List<PrescriptionDTO.GetDto> findByDoctorId(Long doctorId) {
        return prescriptionRepository.findByDoctor_Id(doctorId)
                .stream()
                .map(prescriptionMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO.GetDto> findByPatientId(Long patientId) {
        return prescriptionRepository.findByPatient_Id(patientId)
                .stream()
                .map(prescriptionMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO.GetDto> findByMedicineId(Long medicineId) {
        return prescriptionRepository.findByMedicine_Id(medicineId)
                .stream()
                .map(prescriptionMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO.GetDto> findByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return prescriptionRepository.findByPatient_IdAndDoctor_Id(patientId, doctorId)
                .stream()
                .map(prescriptionMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO.GetDto> findByPatientIdAndMedicineId(Long patientId, Long medicineId) {
        return prescriptionRepository.findByPatient_IdAndMedicine_Id(patientId, medicineId)
                .stream()
                .map(prescriptionMapper::toGetDto)
                .collect(Collectors.toList());
    }
}