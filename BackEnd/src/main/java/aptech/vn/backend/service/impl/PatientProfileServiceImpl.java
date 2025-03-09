package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.PatientProfileDTO;
import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.mapper.PatientProfileMapper;
import aptech.vn.backend.repository.PatientProfileRepository;
import aptech.vn.backend.service.PatientProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {
    private final PatientProfileRepository patientProfileRepository;
    private final PatientProfileMapper patientProfileMapper;

    public PatientProfileServiceImpl(PatientProfileRepository patientProfileRepository, PatientProfileMapper patientProfileMapper) {
        this.patientProfileRepository = patientProfileRepository;
        this.patientProfileMapper = patientProfileMapper;
    }

    @Override
    public List<PatientProfileDTO> findAll() {
        return patientProfileRepository.findAll()
                .stream()
                .map(patientProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientProfileDTO> findById(Long id) {
        return patientProfileRepository.findById(id).map(patientProfileMapper::toDto);
    }

    @Override
    public PatientProfileDTO save(PatientProfileDTO patientProfileDTO) {
        PatientProfile patientProfile = patientProfileMapper.toEntity(patientProfileDTO);
        patientProfileRepository.save(patientProfile);
        return patientProfileMapper.toDto(patientProfile);
    }

    @Override
    public void deleteById(Long id) {
        patientProfileRepository.deleteById(id);
    }

    @Override
    public Optional<PatientProfileDTO> findByUserId(Long userId) {
        return patientProfileRepository.findById(userId).map(patientProfileMapper::toDto);
    }

    @Override
    public List<PatientProfileDTO> findByBloodType(String bloodType) {
        return patientProfileRepository.findByBloodType(bloodType)
                .stream()
                .map(patientProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientProfileDTO> findByMedicalHistoryContaining(String keyword) {
        return patientProfileRepository.findByMedicalHistoryContaining(keyword)
                .stream()
                .map(patientProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientProfileDTO> findByAllergiesContaining(String keyword) {
        return patientProfileRepository.findByAllergiesContaining(keyword)
                .stream()
                .map(patientProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientProfileDTO> findByAccountBalanceGreaterThanEqual(BigDecimal amount) {
        return patientProfileRepository.findByAccountBalanceGreaterThanEqual(amount)
                .stream()
                .map(patientProfileMapper::toDto)
                .collect(Collectors.toList());
    }
}
