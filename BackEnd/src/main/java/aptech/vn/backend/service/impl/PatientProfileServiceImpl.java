package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.PatientProfileDTO;
import aptech.vn.backend.entity.PatientProfile;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.PatientProfileMapper;
import aptech.vn.backend.repository.PatientProfileRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.PatientProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {
    private final PatientProfileRepository patientProfileRepository;
    private final UserRepository userRepository;
    private final PatientProfileMapper patientProfileMapper;

    public PatientProfileServiceImpl(
            PatientProfileRepository patientProfileRepository,
            UserRepository userRepository,
            PatientProfileMapper patientProfileMapper) {
        this.patientProfileRepository = patientProfileRepository;
        this.userRepository = userRepository;
        this.patientProfileMapper = patientProfileMapper;
    }

    @Override
    public List<PatientProfileDTO.GetDto> findAll() {
        return patientProfileRepository.findAll()
                .stream()
                .map(patientProfileMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientProfileDTO.GetDto> findById(Long id) {
        return patientProfileRepository.findById(id)
                .map(patientProfileMapper::toGetDto);
    }

    @Override
    @Transactional
    public PatientProfileDTO.GetDto saveOrUpdate(PatientProfileDTO.SaveDto patientProfileDTO) {
        PatientProfile patientProfile;

        if (patientProfileDTO.getId() == null || patientProfileDTO.getId() == 0) {
            // INSERT case
            patientProfile = new PatientProfile();
            patientProfile.setCreatedAt(LocalDateTime.now());
            patientProfile.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<PatientProfile> existingProfile = patientProfileRepository.findById(patientProfileDTO.getId());
            if (existingProfile.isEmpty()) {
                throw new RuntimeException("Patient profile not found with ID: " + patientProfileDTO.getId());
            }
            patientProfile = existingProfile.get();
            patientProfile.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý user relationship
        User user = userRepository.findById(patientProfileDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + patientProfileDTO.getUserId()));
        patientProfile.setUser(user);

        // Cập nhật các trường khác
        patientProfile.setBloodType(patientProfileDTO.getBloodType());
        patientProfile.setMedicalHistory(patientProfileDTO.getMedicalHistory());
        patientProfile.setAllergies(patientProfileDTO.getAllergies());
        patientProfile.setAccountBalance(patientProfileDTO.getAccountBalance());

        PatientProfile savedProfile = patientProfileRepository.save(patientProfile);
        return patientProfileMapper.toGetDto(savedProfile);
    }

    @Override
    public void deleteById(Long id) {
        patientProfileRepository.deleteById(id);
    }

    @Override
    public Optional<PatientProfileDTO.GetDto> findByUserId(Long userId) {
        return patientProfileRepository.findByUser_Id(userId)
                .map(patientProfileMapper::toGetDto);
    }

    @Override
    public List<PatientProfileDTO.GetDto> findByBloodType(String bloodType) {
        return patientProfileRepository.findByBloodType(bloodType)
                .stream()
                .map(patientProfileMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientProfileDTO.GetDto> findByMedicalHistoryContaining(String keyword) {
        return patientProfileRepository.findByMedicalHistoryContaining(keyword)
                .stream()
                .map(patientProfileMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientProfileDTO.GetDto> findByAllergiesContaining(String keyword) {
        return patientProfileRepository.findByAllergiesContaining(keyword)
                .stream()
                .map(patientProfileMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientProfileDTO.GetDto> findByAccountBalanceGreaterThanEqual(BigDecimal amount) {
        return patientProfileRepository.findByAccountBalanceGreaterThanEqual(amount)
                .stream()
                .map(patientProfileMapper::toGetDto)
                .collect(Collectors.toList());
    }
}