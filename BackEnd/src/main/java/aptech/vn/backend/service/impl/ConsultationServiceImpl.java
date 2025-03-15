package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.Consultation;
import aptech.vn.backend.entity.ConsultationStatus;
import aptech.vn.backend.entity.DoctorProfile;
import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.mapper.ConsultationMapper;
import aptech.vn.backend.repository.ConsultationRepository;
import aptech.vn.backend.repository.DoctorProfileRepository;
import aptech.vn.backend.repository.PatientProfileRepository;
import aptech.vn.backend.service.ConsultationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationMapper consultationMapper;
    private final ConsultationRepository consultationRepository;
    private final DoctorProfileRepository doctorRepository;
    private final PatientProfileRepository patientRepository;

    public ConsultationServiceImpl(
            ConsultationRepository consultationRepository,
            DoctorProfileRepository doctorRepository,
            PatientProfileRepository patientRepository,
            ConsultationMapper consultationMapper) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.consultationMapper = consultationMapper;
    }

    @Override
    public List<ConsultationDTO.GetDto> findAll() {
        return consultationRepository.findAll()
                .stream()
                .map(consultationMapper::toGetDto)  // Đảm bảo sử dụng đúng tên phương thức
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ConsultationDTO.GetDto> findById(Long id) {
        return consultationRepository.findById(id)
                .map(consultationMapper::toGetDto);  // Đảm bảo sử dụng đúng tên phương thức
    }

    @Override
    @Transactional
    public ConsultationDTO.GetDto saveOrUpdate(ConsultationDTO.SaveDto consultationDTO) {
        Consultation consultation;

        if (consultationDTO.getId() == null || consultationDTO.getId() == 0) {
            // INSERT case
            consultation = new Consultation();
            consultation.setCreatedAt(LocalDateTime.now());
            consultation.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Consultation> existingConsultation = consultationRepository.findById(consultationDTO.getId());
            if (existingConsultation.isEmpty()) {
                throw new RuntimeException("Consultation not found with ID: " + consultationDTO.getId());
            }
            consultation = existingConsultation.get();
            consultation.setUpdatedAt(LocalDateTime.now());
        }

         DoctorProfile doctor = doctorRepository.findById(consultationDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + consultationDTO.getDoctorId()));
        consultation.setDoctor(doctor);

         PatientProfile patient = patientRepository.findById(consultationDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + consultationDTO.getPatientId()));
        consultation.setPatient(patient);

         consultation.setConsultationLink(consultationDTO.getConsultationLink());
        consultation.setStatus(consultationDTO.getStatus());



        Consultation savedConsultation = consultationRepository.save(consultation);
        return consultationMapper.toGetDto(savedConsultation);
    }
    @Override
    public void deleteById(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public List<ConsultationDTO.GetDto> findByPatientId(Long patientId) {
        return consultationRepository.findByPatient_Id(patientId)
                .stream()
                .map(consultationMapper::toGetDto)  // Đảm bảo sử dụng đúng tên phương thức
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO.GetDto> findByDoctorId(Long doctorId) {
        return consultationRepository.findByDoctor_Id(doctorId)
                .stream()
                .map(consultationMapper::toGetDto)  // Đảm bảo sử dụng đúng tên phương thức
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO.GetDto> findByStatus(ConsultationStatus status) {
        return consultationRepository.findByStatus(status)
                .stream()
                .map(consultationMapper::toGetDto)  // Đảm bảo sử dụng đúng tên phương thức
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO.GetDto> findByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return consultationRepository.findByPatient_IdAndDoctor_Id(patientId, doctorId)
                .stream()
                .map(consultationMapper::toGetDto)  // Đảm bảo sử dụng đúng tên phương thức
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO.GetDto> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status) {
        return consultationRepository.findByDoctor_IdAndStatus(doctorId, status)
                .stream()
                .map(consultationMapper::toGetDto)  // Đảm bảo sử dụng đúng tên phương thức
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO.GetDto> findByPatientIdAndStatus(Long patientId, ConsultationStatus status) {
        return consultationRepository.findByPatient_IdAndStatus(patientId, status)
                .stream()
                .map(consultationMapper::toGetDto)
                .collect(Collectors.toList());
    }
}