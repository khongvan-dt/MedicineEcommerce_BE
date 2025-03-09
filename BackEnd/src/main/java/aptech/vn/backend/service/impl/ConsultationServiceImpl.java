package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.Consultation;
import aptech.vn.backend.entity.ConsultationStatus;
import aptech.vn.backend.mapper.ConsultationMapper;
import aptech.vn.backend.repository.ConsultationRepository;
import aptech.vn.backend.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, ConsultationMapper consultationMapper) {
        this.consultationRepository = consultationRepository;
        this.consultationMapper = consultationMapper;
    }

    @Override
    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ConsultationDTO> findById(Long id) {
        return consultationRepository.findById(id)
                .map(consultationMapper::toDto);
    }

    @Override
    public ConsultationDTO save(ConsultationDTO consultationDTO) {
        Consultation consultation = consultationMapper.toEntity(consultationDTO);
        Consultation savedConsultation = consultationRepository.save(consultation);
        return consultationMapper.toDto(savedConsultation);
    }

    @Override
    public void deleteById(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public List<ConsultationDTO> findByPatientId(Long patientId) {
        return consultationRepository.findByPatient_Id(patientId).stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByDoctorId(Long doctorId) {
        return consultationRepository.findByDoctor_Id(doctorId).stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByStatus(ConsultationStatus status) {
        return consultationRepository.findByStatus(status).stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return consultationRepository.findByPatient_IdAndDoctor_Id(patientId, doctorId).stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status) {
        return consultationRepository.findByDoctor_IdAndStatus(doctorId, status).stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByPatientIdAndStatus(Long patientId, ConsultationStatus status) {
        return consultationRepository.findByPatient_IdAndStatus(patientId, status).stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }
}